package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

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

        jt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!jt.isChecked()){
                    jt.setChecked(false);
                    rt.setChecked(true);
                    jt.setBackgroundTintList(getResources().getColorStateList(R.color.primaryText));
                    rt.setBackgroundTintList(getResources().getColorStateList(R.color.text_icons));
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
                }
            }
        });

    }
}
