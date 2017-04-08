package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Luigi on 4/8/17.
 */

public class ParentChildView extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parentchild_view);

        setListeners();
    }

    void setListeners(){
        View card = (View) findViewById(R.id.ViewTask_card);

        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent;
                intent = new Intent(ParentChildView.this, ParentViewTasks.class);
                startActivity(intent);

            }

        });

        card = findViewById(R.id.ViewRew_card);

        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent;
                intent = new Intent(ParentChildView.this, ParentViewRewards.class);
                startActivity(intent);

            }

        });
    }
}
