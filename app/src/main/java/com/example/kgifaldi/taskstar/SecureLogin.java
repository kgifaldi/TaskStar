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
    public static Parent parent = MainActivity.parent_obj;
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
                this_parent = (Parent) ParentLogin.parent_obj;
                startActivity(intent);

            }

        });
    }
}
