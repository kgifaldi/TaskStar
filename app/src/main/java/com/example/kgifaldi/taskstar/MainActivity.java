package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends Activity {

    void setCardListener(int cardId, final String className){
        View card = (View) findViewById(cardId);

        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                // TODO change to convert string to .class ...
                Intent intent;
                if(className == "ParentLogin") {
                   intent = new Intent(MainActivity.this, SecureLogin.class);
                   // intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
                    // startActivity(intent, REQUEST_CODE_ENABLE);
                    startActivity(intent);

                }
                else {
                    intent = new Intent(MainActivity.this, ChildLogin.class);
                    startActivity(intent);
                }

            }

        });

    }

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCardListener(R.id.parent_card, "ParentLogin");
        setCardListener(R.id.child_card, "ChildLogin");
    }
}
