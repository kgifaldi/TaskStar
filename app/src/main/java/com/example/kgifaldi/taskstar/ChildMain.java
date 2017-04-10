package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChildMain extends Activity {


    void setCardListener(int cardId, final String className){
        View card = (View) findViewById(cardId);

        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                // TODO change to convert string to .class ...
                Intent intent;
                if(className == "TodaysTasks")
                    intent = new Intent(ChildMain.this, TodaysTasks.class);
                else if(className == "RewardsStore")
                    intent = new Intent(ChildMain.this, RewardsStore.class);
                else
                    intent = new Intent(ChildMain.this, RedeemReward.class);
                startActivity(intent);
            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_main);
        setCardListener(R.id.task_card, "TodaysTasks");
        setCardListener(R.id.reward_card, "RewardsStore");
        setCardListener(R.id.performance_card, "RedeemReward");
    }


}
