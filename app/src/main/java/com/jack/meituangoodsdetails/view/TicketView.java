package com.jack.meituangoodsdetails.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jack.meituangoodsdetails.R;

public class TicketView extends FrameLayout {
    private LayoutInflater layoutInflater;
    private View view;

    private int ticketWidth;
    private int ticketHeight;
    private float ticketMoneySize;
    private float receiveTicketSize;
    private float ticketTimeSize;

    private int animatorWidth;
    private int animatorHeight;
    private float animtorTicketMoney;
    private float animatorReceiveTicket;
    private float animatorTicketTime;

    private boolean first=true;
    private TextView ticketMoney;
    private TextView ticketTime;
    private TextView receiveTicket;

    public TicketView(Context context) {
        this(context,null);
    }

    public TicketView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TicketView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.layout_ticket_view,null);
        ticketMoney=view.findViewById(R.id.ticket_money);
        ticketTime=view.findViewById(R.id.ticket_time);
        receiveTicket=view.findViewById(R.id.receive_ticket);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(first){
                    ticketWidth=view.getWidth();
                    ticketHeight=view.getHeight();
                    ticketMoneySize=ticketMoney.getTextSize();
                    ticketTimeSize=ticketTime.getTextSize();
                    receiveTicketSize=receiveTicket.getTextSize();

                    RelativeLayout tickerView=view.findViewById(R.id.ticket_view);
                    LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams)tickerView.getLayoutParams();
                    layoutParams.height=0;
                    layoutParams.width=0;
                    tickerView.setLayoutParams(layoutParams);

                    first=false;
                }
            }
        });
        addView(view);
    }

    public void animatorView(int dy){
        //dy<0向下 展示,dy>0向上 隐藏
        animatorWidth-=(ticketWidth/ticketHeight)*dy;
        animatorHeight-=dy;
        animtorTicketMoney-=dy;
        animatorTicketTime-=dy;
        animatorReceiveTicket-=dy;

        if(animatorWidth<=0){
            animatorWidth=0;
        }else if(animatorWidth>=ticketWidth){
            animatorWidth=ticketWidth;
        }

        if(animatorHeight<=0){
            animatorHeight=0;
        }else if(animatorHeight>=ticketHeight){
            animatorHeight=ticketHeight;
        }
        RelativeLayout tickerView=view.findViewById(R.id.ticket_view);

        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams)tickerView.getLayoutParams();
        layoutParams.height=animatorHeight;
        layoutParams.width=animatorWidth;
        tickerView.setLayoutParams(layoutParams);

        LayoutParams layoutParams2=(LayoutParams)view.getLayoutParams();
        layoutParams2.height=animatorHeight;
        layoutParams2.width=animatorWidth;
        layoutParams2.setMargins(10,0,0,0);
        view.setLayoutParams(layoutParams2);

        if(animtorTicketMoney<=0){
            animtorTicketMoney=0;
        }else if(animtorTicketMoney>=ticketMoneySize){
            animtorTicketMoney=ticketMoneySize;
        }
        ticketMoney.setTextSize(TypedValue.COMPLEX_UNIT_PX,animtorTicketMoney);

        if(animatorTicketTime<=0){
            animatorTicketTime=0;
        }else if(animatorTicketTime>=ticketTimeSize){
            animatorTicketTime=ticketTimeSize;
        }
        ticketTime.setTextSize(TypedValue.COMPLEX_UNIT_PX,animatorTicketTime);

        if(animatorReceiveTicket<=0){
            animatorReceiveTicket=0;
        }else if(animatorReceiveTicket>=receiveTicketSize){
            animatorReceiveTicket=receiveTicketSize;
        }
        receiveTicket.setTextSize(TypedValue.COMPLEX_UNIT_PX,animatorReceiveTicket);

    }

}
