package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

/**
 * Created by kgifaldi on 4/13/17.
 */

public class SecureLogin extends Activity {

    public static int parentId = -1;
    private static Parent this_parent = new Parent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secure_login);

        /*

        TODO: set parentId once logged in so that other classes can access it

         */


        Button but = (Button) findViewById(R.id.login);
        but.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                // TODO change to convert string to .class ...
                Intent intent;
                intent = new Intent(SecureLogin.this, ParentMain.class);

                Intent parent_main_intent = getIntent();
                this_parent = (Parent) parent_main_intent.getSerializableExtra("parent");

                Bundle bundle = new Bundle();
                bundle.putSerializable("parent", (Serializable) this_parent);

                intent.putExtras(bundle);


                startActivity(intent);

            }

        });

    }



}
