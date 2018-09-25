package com.jack.meituangoodsdetails.hehavior;

import com.jack.meituangoodsdetails.fragment.GoodsListFragment;
import com.jack.meituangoodsdetails.util.DensityUtils;
import com.jack.meituangoodsdetails.view.GoodDetailsView;
import com.jack.meituangoodsdetails.view.GoodsListView;
import com.jack.meituangoodsdetails.view.GoodsTitleView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.annotation.NonNull;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Scroller;
import android.os.Handler;
import android.view.View;

public class GoodsListBehavior extends CoordinatorLayout.Behavior<GoodsListView> {
    private CoordinatorLayout parentView;
    private GoodDetailsView detailsView;
    private GoodsTitleView titleView;
    private GoodsListView goodView;
    private Context context;
    private Scroller scroller;
    private int duration=1000;
    private Handler handler;

    private int pagingTouchSlop;
    private int verticalPagingTouch;

    //商品界面的中心
    int centerGoodView;
    //商品界面离顶部的间隔
    int goodViewTop;

    public GoodsListBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
        this.context=context;
        this.pagingTouchSlop=DensityUtils.dp2px(context,5);
        this.scroller=new Scroller(context);
        this.handler=new Handler();
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull GoodsListView child, @NonNull View dependency) {
        this.goodView=child;
        this.parentView=parent;
        if(dependency instanceof GoodsTitleView){
            titleView=(GoodsTitleView) dependency;
            return true;
        }
        if(dependency instanceof GoodDetailsView){
            detailsView=(GoodDetailsView) dependency;
            detailsView.expandBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startScroll((int)goodView.getTranslationY(),goodViewTop-parentView.getHeight());
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull GoodsListView child, int layoutDirection) {
        CoordinatorLayout.LayoutParams layoutParams=(CoordinatorLayout.LayoutParams)child.getLayoutParams();
        if(layoutParams.height==CoordinatorLayout.LayoutParams.MATCH_PARENT){
            layoutParams.height=parent.getHeight()-titleView.getHeight();
            child.setLayoutParams(layoutParams);
            goodViewTop=titleView.getHeight()+ DensityUtils.dp2px(context,160);
            child.setTranslationY(goodViewTop);
            return true;
        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull GoodsListView child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        handler.removeCallbacks(flingRunnable);
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL)!=0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull GoodsListView child,
                                  @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        //防止左右误滑
        verticalPagingTouch+=dy;
        if(goodView.viewPager.isScrollable()&&Math.abs(verticalPagingTouch)>pagingTouchSlop){
            goodView.viewPager.setScrollable(false);
        }

        if(dy>0){
            //向上滑
            if(child.getTranslationY()<=titleView.getHeight()){
                child.setTranslationY(titleView.getHeight());
            }else{
                child.setTranslationY(child.getTranslationY()-dy);
                consumed[1]=dy;
            }
        }else{
            //向下滑
            if(((GoodsListFragment) child.getFragment().get(child.viewPager.getCurrentItem())).isScrollAble()){
                child.setTranslationY(child.getTranslationY()-dy);
            }
        }

        if(child.getTranslationY()>=goodViewTop){
            detailsView.updateView(dy);
            titleView.checkView();
        } else{
            titleView.updateView(dy);
        }

    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout parent,
                                   @NonNull GoodsListView child,
                                   @NonNull View target, int type) {
        verticalPagingTouch = 0;
        goodView.viewPager.setScrollable(true);
        centerGoodView=(parent.getHeight()+goodViewTop)/2;

//        if(child.getTranslationY()<centerHeadMargin){
//            //滑到顶部
//        }else if(child.getTranslationY()>centerHeadMargin&&child.getTranslationY()<headMarginTop){
//            //恢复
//        }else

        if(child.getTranslationY()>goodViewTop&&child.getTranslationY()<centerGoodView){
            //恢复
            startScroll((int)child.getTranslationY(),(int)(goodViewTop-child.getTranslationY()));
        }else if(child.getTranslationY()>centerGoodView){
            //隐藏
            startScroll((int)child.getTranslationY(),(int)(parent.getHeight()-child.getTranslationY()));
        }

    }

    @Override
    public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull GoodsListView child, @NonNull View target, float velocityX, float velocityY, boolean consumed) {
        if(velocityY<0){
            //向下
            startScroll((int)child.getTranslationY(),(int)(coordinatorLayout.getHeight()-child.getTranslationY()));
        }else{
            //向上
            if(goodView.getTranslationY()<goodViewTop){
                startScroll((int)child.getTranslationY(),(int)(titleView.getHeight()-child.getTranslationY()));
            }else{
                startScroll((int)child.getTranslationY(),(int)(goodViewTop-child.getTranslationY()));
            }
        }
        return true;
    }

    public void startScroll(int startY,int dy){
        scroller.startScroll(0,startY,0,dy,duration);
        this.handler.post(flingRunnable);
    }

    Runnable flingRunnable=new Runnable() {
        @Override
        public void run() {
            if(scroller.computeScrollOffset()){
                goodView.setTranslationY(scroller.getCurrY());
                if(goodView.getTranslationY()>=goodViewTop){
                    detailsView.updateView(scroller.getStartY()-scroller.getFinalY());
                }else{
                    titleView.updateView(scroller.getStartY()-scroller.getFinalY());
                }
                handler.post(flingRunnable);
            }
        }
    };

}
