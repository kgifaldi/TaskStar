package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.orangegangsters.lollipin.lib.PinActivity;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.AppLockActivity;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;

import java.io.Serializable;

/**
 * Created by kgifaldi on 4/13/17.
 */

public  class SecureLogin extends Activity {


    public static int parentId = -1;
    private static Parent this_parent = new Parent();
    public static Parent parent = MainActivity.parent_obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secure_login);
        final Context context = getApplicationContext();

        /*
        TODO: set parentId once logged in so that other classes can access it
         */

        Button but = (Button) findViewById(R.id.login);
        but.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                EditText et = (EditText)findViewById(R.id.password_enter);
                String pass = et.getText().toString();
                System.out.println("PASSWORD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println(pass);
                System.out.println(PublicData.password);
                if(pass.equals(PublicData.password)) {
                    System.out.println("Correct password");

                    // TODO change to convert string to .class ...
                    Intent intent;
                    intent = new Intent(SecureLogin.this, ParentMain.class);
                    this_parent = (Parent) ParentLogin.parent_obj;
                    startActivity(intent);
                    finish();
                }
                else{
                    System.out.println("incorrect");

                    Toast t = new Toast(getApplicationContext());
                    t.makeText(getApplicationContext(), "Incorrect Password. Try Again...", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

}
