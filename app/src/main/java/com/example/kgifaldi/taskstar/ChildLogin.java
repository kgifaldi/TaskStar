package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.io.BufferedReader;
import java.util.jar.Attributes;

public class ChildLogin extends Activity {
    String[] children = {"Ben", "Emma", "Ethan", "Nick", "Iris", "Sarah", "Henry"};

    LinearLayout ll;

    void setCardListener(int cardId){
        View card = (View) findViewById(cardId);

        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent;
                intent = new Intent(ChildLogin.this, ChildMain.class);
                startActivity(intent);

            }

        });

    }


    private int getMatColor(String typeColor)
    {
        int returnColor = Color.BLACK;
        int arrayId = getResources().getIdentifier("mdcolor_" + typeColor, "array", getApplicationContext().getPackageName());

        if (arrayId != 0)
        {
            TypedArray colors = getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.BLACK);
            colors.recycle();
        }
        return returnColor;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_login);

        // grab existing linear layout (within child_login.xml) so that we can add our Views to it later
        ll = (LinearLayout) findViewById(R.id.child_list);

        // some variables used to format xml elements
        int cardHeight = 500;
        int txtSz = 40;
        int tempId; // tempId used when generating new id for each CardView
        // add Children cards to child_login:
        for(int i = 0; i < children.length; i++) {

            // set lp to linear layouts params to pass to cards
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp_txt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 40, 0, 0);

            // instantiate card view and set some values
            CardView tmp = new CardView(this);
            // give card unique id for future referencing:
            tempId = View.generateViewId();
            tmp.setId(tempId);
            tmp.setLayoutParams(lp);
            tmp.setClickable(true);
            tmp.setMaxCardElevation(25);
            tmp.setCardElevation(15);
            tmp.setMinimumHeight(cardHeight);

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
            NameText.setText(children[i]);
            NameText.setTextSize(txtSz);
            NameText.setPadding(0, (cardHeight - txtSz)/4, 0, 0);
            NameText.setTextColor(getResources().getColor(R.color.primaryText));

            String letter = (NameText.getText().charAt(0)+"");
            TextDrawable drbl = TextDrawable.builder().buildRound(letter, getMatColor("500"));
            ImageView childImg = new ImageView(this);
            childImg.setImageDrawable(drbl);

            // finally, add text and image to cardView and add cardView to linear layout within child_login xml file
            tmp.addView(childImg);
            tmp.addView(NameText);
            ll.addView(tmp);

            // add onClickListener to CardViews
            setCardListener(tempId);

        }
    }
}

