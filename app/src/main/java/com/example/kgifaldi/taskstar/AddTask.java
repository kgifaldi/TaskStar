package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.amulyakhare.textdrawable.TextDrawable;

/**
 * Created by Luigi on 4/8/17.
 */

public class AddTask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtaskview);

        final ToggleButton jt = (ToggleButton)findViewById(R.id.today_toggle);
        final ToggleButton rt = (ToggleButton)findViewById(R.id.repeat_toggle);
        final LinearLayout wl = (LinearLayout)findViewById(R.id.weekly_list);
        jt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!jt.isChecked()){
                    jt.setChecked(false);
                    rt.setChecked(true);
                    jt.setBackgroundTintList(getResources().getColorStateList(R.color.primaryText));
                    rt.setBackgroundTintList(getResources().getColorStateList(R.color.text_icons));
                    wl.setVisibility(View.INVISIBLE);
                }
            }
        });

        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rt.isChecked()){
                    rt.setChecked(false);
                    jt.setChecked(true);
                    jt.setBackgroundTintList(getResources().getColorStateList(R.color.text_icons));
                    rt.setBackgroundTintList(getResources().getColorStateList(R.color.primaryText));
                    wl.setVisibility(View.VISIBLE);
                }
            }
        });
        int txtSz = 40;

        //LinearLayout cl = (LinearLayout)findViewById(R.id.child_task_list);

        for(int i = 0; i < ChildLogin.children.length; i++) {

            ViewGroup.LayoutParams tb_l = new LinearLayout.LayoutParams(

                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );
            tb_l.width = 250;
            tb_l.height = 250;

            GradientDrawable gd = new GradientDrawable();
            gd.setCornerRadius(100);
            gd.setColor(getResources().getColor(R.color.colorSecondary));


            ToggleButton tb = new ToggleButton(this);
            tb.setText(ChildLogin.children[i]);
            tb.setLayoutParams(tb_l);

            tb.setBackground(gd);

            tb.setTop(5000);
            tb.setLeft(5000);
            tb.setRight(5000);
            tb.setBottom(5000);

            //cl.addView(tb);

        }

    }
}
