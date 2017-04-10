package com.example.kgifaldi.taskstar;

import android.widget.Button;

/**
 * Created by kgifaldi on 4/9/17.
 */

public class Task {

    private String question;
    private Button yes;
    private Button no;

    public Task(String question){

        this.question = question;

    }

    public String getQuestion(){return question;}

}
