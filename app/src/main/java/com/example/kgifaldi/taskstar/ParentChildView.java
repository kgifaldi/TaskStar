package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by Luigi on 4/8/17.
 */

public class ParentChildView extends Activity {

    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.parentchild_view);

        // grab existing linear layout (within child_login.xml) so that we can add our Views to it later
        ll = (LinearLayout) findViewById(R.id.pc_view);
        // some variables used to format xml elements
        int cardHeight = 300;
        int txtSz = 35;
        int tempId; // tempId used when generating new id for each CardView

        // set lp to linear layouts params to pass to cards
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams lp_txt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 40, 0, 0);

        // instantiate card view and set some values
        CardView tmp = new CardView(this);
        tmp.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
        // give card unique id for future referencing:
        tempId = View.generateViewId();
        tmp.setId(tempId);
        tmp.setLayoutParams(lp);
        tmp.setClickable(true);
        tmp.setMaxCardElevation(25);
        tmp.setCardElevation(15);
        tmp.setMinimumHeight(cardHeight);

        tmp.setTransitionName(getString(R.string.transition_string));

        // add ripple effect to card!
        ColorStateList csl = ColorStateList.valueOf(getResources().getColor(R.color.secondaryText));
        RippleDrawable d = new RippleDrawable(csl, null, null);
        tmp.setForeground(d);

        // initialize TextView to place into Child Card
        TextView NameText = new TextView(this);
        NameText.setLayoutParams(lp);
        NameText.setText(ParentMain.curr_name);
        NameText.setTextSize(txtSz);
        NameText.setPadding(350, 55, 0, 0);
        NameText.setTextColor(getResources().getColor(R.color.colorSecondary));
        int randomColor = ParentMain.curr_color;

        String letter = ParentMain.curr_name.trim().substring(0, 1);
        System.out.println("letter thing: " + letter);
        System.out.println("name for child: " + ParentMain.curr_name);
        TextDrawable drbl = TextDrawable.builder().buildRound(letter, randomColor);
        ImageView childImg = new ImageView(this);

        childImg.setImageDrawable(drbl);
        int width = 350;
        int height = 350;
        childImg.setPadding(50, 50, 50, 50);
        LinearLayout.LayoutParams prms = new LinearLayout.LayoutParams(width, height);
        childImg.setLayoutParams(prms);


        // finally, add text and image to cardView and add cardView to linear layout within child_login xml file
        tmp.addView(childImg);
        tmp.addView(NameText);
        ll.addView(tmp);

        /* List View Stuff */


        Child this_child = new Child();
        for (Child each_child : PublicData.children_list){
            if (each_child.getUsername().trim().equals(ParentMain.curr_name.trim())){
                this_child = each_child;
                break;
            }
        }

        // Reward Balance
        // Card view for each task
        CardView reward_balance_card = new CardView(this);
        reward_balance_card.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tempId = View.generateViewId();
        reward_balance_card.setId(tempId);
        reward_balance_card.setLayoutParams(lp);
        reward_balance_card.setClickable(false);
        reward_balance_card.setCardElevation(0);

        // Edit text view for each task card
        TextView reward_text = new TextView(this);
        reward_text.setLayoutParams(lp);
        reward_text.setText("REWARD BALANCE: " + this_child.getBalance());
        reward_text.setTextSize(30);
        reward_text.setPadding(20, 20, 20, 20);
        reward_text.setTextColor(Color.BLACK);

        // Add the text view to the card
        reward_balance_card.addView(reward_text);
        ll.addView(reward_balance_card);

        // Card view for each task
        CardView general_task_card = new CardView(this);
        general_task_card.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        tempId = View.generateViewId();
        general_task_card.setId(tempId);
        general_task_card.setLayoutParams(lp);
        general_task_card.setClickable(false);
        general_task_card.setCardElevation(0);

        // Edit text view for each task card
        TextView generaltasktext = new TextView(this);
        generaltasktext.setLayoutParams(lp);
        generaltasktext.setText("TASKS ASSIGNED");
        generaltasktext.setTextSize(30);
        generaltasktext.setPadding(20, 20, 20, 20);
        generaltasktext.setTextColor(Color.BLACK);

        // Add the text view to the card
        general_task_card.addView(generaltasktext);
        ll.addView(general_task_card);


        for (TaskClass each_task : this_child.getTaskList()){

            // Card view for each task
            CardView task_card = new CardView(this);
            task_card.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tempId = View.generateViewId();
            task_card.setId(tempId);
            task_card.setLayoutParams(lp);
            task_card.setClickable(false);
            task_card.setCardElevation(0);

            // Edit text view for each task card
            TextView TaskText = new TextView(this);
            TaskText.setLayoutParams(lp);
            TaskText.setText(" - " + each_task.getName());
            TaskText.setTextSize(20);
            TaskText.setPadding(20, 20, 20, 20);
            TaskText.setTextColor(Color.BLACK);

            // Add the text view to the card
            task_card.addView(TaskText);
            ll.addView(task_card);

        }


    }


}