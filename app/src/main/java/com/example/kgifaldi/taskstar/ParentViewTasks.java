package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

/**
 * Created by Luigi on 4/8/17.
 */

public class ParentViewTasks extends Activity {
    String[] tasks = {"Task1", "Taks2", "Task3", "Task4", "Task5"};


    /*
    TODO: delete above array

    1) get parentId from seceureLogin.parendId
    2) access childId from ParentMain.childId
    3) use ParentMain.ChildId to access specific childs rewards from database {get childId rewards from database}

     */

    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parentsviewtask);

        // grab existing linear layout (within child_login.xml) so that we can add our Views to it later
        ll = (LinearLayout) findViewById(R.id.task_list);

        // some variables used to format xml elements
        int cardHeight = 250;
        int txtSz = 40;
        int tempId; // tempId used when generating new id for each CardView
    }
}
