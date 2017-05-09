package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.jar.Attributes;

/**
 * Created by Luigi on 4/8/17.
 */

public class ParentChildView extends Activity{

    String[] children = {"Ben", "Emma", "Ethan", "Nick", "Iris", "Sarah", "Henry"};
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

        // initialize ImageView to place into Child Card
        // TODO: dynamically select child photo, rn generic photo is used
        // ImageView childImg = new ImageView(this);
        //childImg.setLayoutParams(lp);

        // childImg.setImageResource(R.drawable.genchild);
        //childImg.setMaxWidth(cardHeight/2);
        //childImg.setMinimumHeight(cardHeight/2);
        //childImg.setMaxHeight(cardHeight/2);
        //childImg.setAdjustViewBounds(true);



        // TODO: formatting image and text with each child card...

        // initialize TextView to place into Child Card
        TextView NameText = new TextView(this);
        NameText.setLayoutParams(lp);
        NameText.setText(ParentMain.curr_name);
        NameText.setTextSize(txtSz);
        NameText.setPadding(350, 55, 0, 0);
        NameText.setTextColor(getResources().getColor(R.color.colorSecondary));
        int randomColor = ParentMain.curr_color;



        String letter = (NameText.getText().charAt(0)+"");
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

        // add onClickListener to CardViews
    }
}