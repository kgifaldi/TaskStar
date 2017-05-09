package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.animation.AnimatorListenerAdapter;
import com.wenchao.cardstack.CardStack;
import com.wenchao.cardstack.CardAnimator;
import com.wenchao.cardstack.CardUtils;
import com.wenchao.cardstack.DragGestureDetector;

import java.util.ArrayList;

// TODO
public class TodaysTasks extends Activity implements CardStack.CardEventListener {

    public static CardStack todays_tasks;
    private CardAdapter card_adapter;
    int leftToDo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todays_tasks);

        // init cards is currently used for testing and hards codes some cards into the stack view
        initCards();

        // initialize our Card stack and set some attributes including its adapter
        todays_tasks = (CardStack) findViewById(R.id.task_stack);
        todays_tasks.setContentResource(R.layout.single_task);
        todays_tasks.setStackMargin(20);
        todays_tasks.setAdapter(card_adapter);

        todays_tasks.setListener(this);
    }
    private ArrayList<Task> questions = new ArrayList<>();

    private void initCards(){
        card_adapter = new CardAdapter(getApplicationContext(), 0, questions);
/*
        questions.add(new Task("Did you do laundry?"));
        questions.add(new Task("Have you cleaned your room?"));
        questions.add(new Task("Have you completed your math homework?"));
        questions.add(new Task("Did you have a great day?"));
*/
        ArrayList<TaskClass> task_list = PublicData.selected_child.getTaskList();
        leftToDo = task_list.size();
        for(int i = 0; i < leftToDo; i++){
            questions.add(new Task("Did you" + task_list.get(i).getName() +"?"));
        }

        /*

        TODO:

        delete above
        1) String[] tasks = {get this from database using childs id (access childId from the ChildLogin: variable name is ChildLogin.currChild}
        2) 2 options
            1. have an array of bools for tasks is done or not done
            2. have a helper function which deletes task once swiped left

         */
    }


    // swipe end occurs when user lifts touch of card
    @Override
    public boolean swipeEnd(int i, float v) {

        // if the card position is past some point, then we will return true (chop it off the stack) and deal with it depending on which direction user swiped the card
        if(v > 300){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean swipeStart(int i, float v) {
        return false;
    }

    @Override
    public boolean swipeContinue(int i, float v, float v1) {
        return false;
    }

    @Override
    public void discarded(int i, int i1) {

        if(i1 == 0 || i1 == 2) // direction 0 and 2 are LEFT (whereas 1 and 3 are right)

            card_adapter.add(card_adapter.getItem(0)); // so if LEFT, then add to back of stack
        else if(leftToDo == 1){
            /*
            Intent intent = new Intent(TodaysTasks.this, ChildMain.class); // otherwise, if right and end of stack, start main child activity because child has completed all tasks!
            startActivity(intent);
            */
            finish();
        }
        else {
            leftToDo--;
        }

    }

    @Override
    public void topCardTapped() {



    }
}