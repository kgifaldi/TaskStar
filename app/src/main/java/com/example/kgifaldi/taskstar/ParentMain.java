package com.example.kgifaldi.taskstar;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView.*;
import com.amulyakhare.textdrawable.TextDrawable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Luigi on 4/8/17.
 */

public class ParentMain extends Activity {

    private static String parent_image_id;

    private static Child [] children_obj_array;

    public static int childId = -1;
    /* TODO: parent name needs list of childrens names: erase above string[]

        1) String[] children = {get this from database}
        2) onclick childClick, set childId so that other classes have access to it

     */

    LinearLayout ll;

    public static View glob_card;
    public static String name_card;
    public static Drawable color_card;
    public static int curr_child;
    public static int curr_color;
    public static String curr_name;
    public static char curr_letter;
    DBHelper dbHelper;

    private static Parent this_parent = new Parent();

    View but; // ImageButton imageButton
    ScrollView scrollView; // ImageView imageView
    LinearLayout revealView, layoutButtons; // linearView(id):
    Animation alphaAnimation;
    float pixelDensity;
    public String FLAG = "plus"; // plus if FAB is plus; exit if FAB is exit


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_main);

        // Get the bundle passed into main parent
        Intent parent_main_intent = getIntent();
        this_parent = (Parent) parent_main_intent.getSerializableExtra("parent");

        String parent_id = this_parent.getId();

        // DATABASE HELPER ----------------------------------------------
        dbHelper = new DBHelper(this.getApplicationContext());
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);
        // ----------------------------------------------------------------------

        ArrayList<Child> children_array_list = dbHelper.get_children_from_db(parent_id);

        System.out.println(children_array_list);


        scrollView = (ScrollView) findViewById(R.id.ScrollView01);
        but = (View) findViewById(R.id.AddChildFAB);
        revealView = (LinearLayout)findViewById(R.id.linearView);
        alphaAnimation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        pixelDensity = getResources().getDisplayMetrics().density;
        layoutButtons = (LinearLayout)findViewById(R.id.layoutButtons);

        // grab existing linear layout (within child_login.xml) so that we can add our Views to it later
        ll = (LinearLayout) findViewById(R.id.parentchild_list);



        // some variables used to format xml elements
        int cardHeight = 300;
        int txtSz = 40;
        int tempId; // tempId used when generating new id for each CardView
        // add Children cards to child_login:

        scrollView = (ScrollView)findViewById(R.id.ScrollView01);
        for(Child each_child : children_array_list) {

            // set lp to linear layouts params to pass to cards
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp_txt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 50, 0, 0);

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
            NameText.setText(each_child.getUsername());
            NameText.setTextSize(txtSz);
            NameText.setPadding(450, 65, 0, 0);
            NameText.setTextColor(getResources().getColor(R.color.colorSecondary));
            int randomColor = MaterialColorPalette.getRandomColor("500");



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
            setCardListener(tempId, randomColor, each_child.getUsername(), each_child.getUsername().charAt(0));

            enterReveal(childImg);
        }



        enterReveal(but);



        setListeners();



    }

    void enterReveal(final View v){

        v.post(new Runnable() {
            @Override
            public void run() {

                int cx = v.getMeasuredWidth() / 2;
                int cy = v.getMeasuredHeight() / 2;

                int finRad = Math.max(v.getWidth(), v.getHeight()) / 2;

                Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finRad);
                v.setVisibility(View.VISIBLE);
                anim.setDuration(500);
                anim.start();

                }
        });
    }


    public void animateIntent(View v){
        v.setId(R.id.glob_card);

        Intent intent;
        intent = new Intent(this, ParentChildView.class);


        String transitionName = getString(R.string.transition_string);
        System.out.println(transitionName);

        glob_card = v;

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ParentMain.this, v, transitionName);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }


    void setCardListener(final int cardId, final int color, final String childName, final char letter){

        View card = (View) findViewById(cardId);
        color_card = card.getBackground();

        System.out.println("---------------------------------------------------------");
        System.out.println(cardId);

        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                System.out.println("COLOR, NAME:");
                System.out.println(name_card);

                curr_color = color;
                curr_name = childName;
                curr_letter = letter;
                curr_child = cardId;

                animateIntent(v);

            }

        });

    }

    // TODO: BUG when you click the FAB too fast, the list of children stays hidden. Not important

    void setListeners(){
        View card = (View) findViewById(R.id.AddTask_card);

        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent = new Intent(ParentMain.this, AddTask.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("parent", (Serializable) this_parent);
                intent.putExtras(bundle);
                startActivity(intent);

            }

        });

        card = findViewById(R.id.AddRew_card);

        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent intent;
                intent = new Intent(ParentMain.this, AddReward.class);
                startActivity(intent);

            }

        });

        final View FAB = findViewById(R.id.AddChildFAB);


        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int x = scrollView.getRight();
                int y = scrollView.getBottom();
                x-=((28*pixelDensity) + (16*pixelDensity));

                int hypotenuse = (int) Math.hypot(scrollView.getWidth(), scrollView.getHeight());

                if(FLAG == "plus"){

                    // using tutorial @ following blog post: http://anjithsasindran.in/blog/2015/08/15/material-sharing-card/

                    RelativeLayout.LayoutParams parameters = (RelativeLayout.LayoutParams)revealView.getLayoutParams();
                    parameters.height = scrollView.getHeight();
                    revealView.setLayoutParams(parameters);


                    Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, 0, hypotenuse);
                    anim.setDuration(600);

                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            layoutButtons.setVisibility(View.VISIBLE);
                            layoutButtons.startAnimation(alphaAnimation);
                            FAB.setBackground(getResources().getDrawable(R.drawable.ic_close_black_24dp));
                            ImageView iFAB = (ImageView)FAB;
                            iFAB.setImageResource(R.drawable.ic_close_black_24dp);
                            ((ImageView) FAB).setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            scrollView.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });


                    revealView.setVisibility(View.VISIBLE);
                    anim.start();
                    FLAG = "exit";
                }
                else{

                    Animator anim = ViewAnimationUtils.createCircularReveal(revealView, x, y, hypotenuse, 0);
                    anim.setDuration(500);

                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            scrollView.setVisibility(View.VISIBLE);
                            FAB.setBackground(getResources().getDrawable(R.drawable.ic_add_black_24dp));
                            FAB.setBackgroundResource(R.drawable.ic_add_black_24dp);
                            FAB.setBackgroundColor(Color.BLACK);
                            FAB.setBackgroundColor(getResources().getColor(R.color.red));
                            ImageView iFAB = (ImageView)FAB;
                            iFAB.setImageResource(R.drawable.ic_add_black_24dp);
                            iFAB.setBackgroundColor(getResources().getColor(R.color.red));
                            ((ImageView) FAB).setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.cardview_light_background)));
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            revealView.setVisibility(View.GONE);
                            layoutButtons.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });


                    anim.start();

                    FLAG = "plus";
                }
            }
        });

    }
}
