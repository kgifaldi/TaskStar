package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;


public class MainActivity extends Activity {
    public static Parent parent_obj = ParentLogin.parent_obj;
    private static Parent this_parent = new Parent();

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

                    Intent parent_main_intent = getIntent();
                    this_parent = (Parent) parent_main_intent.getSerializableExtra("parent");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("parent", (Serializable) this_parent);

                    intent.putExtras(bundle);



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
