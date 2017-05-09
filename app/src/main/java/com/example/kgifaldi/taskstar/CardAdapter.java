package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.wenchao.cardstack.CardStack;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.example.kgifaldi.taskstar.R.layout.todays_tasks;


public class CardAdapter extends ArrayAdapter<Task> {
    //LayoutInflater l;
    private Context context;
    private List<Task> tskList;
     int color = 0;

    public CardAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<Task> objects) {
        super(context, resource, objects);

        this.context = context;
        this.tskList = objects;
        // l = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Task rl = tskList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.single_task, null);
        if(color == 0) {
            view.setBackgroundResource(R.color.colorPrimary);
            color = 1;
        }
        else {
            view.setBackgroundResource(R.color.colorSecondary);
            color = 0;
        }
        final TextView txtView = (TextView) view.findViewById(R.id.quesiton);
        Button yes = (Button) view.findViewById(R.id.yes_button);
        Button no = (Button) view.findViewById(R.id.no_button);

        yes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TodaysTasks.todays_tasks.discardTop(1);
                PublicData.selected_child.complete_task(txtView.getText().toString());
            }
        });
        no.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TodaysTasks.todays_tasks.discardTop(0);
            }
        });
        txtView.setText(rl.getQuestion());

        //TextView txtView = (TextView) convertView.findViewById(R.id.quesiton);
        //txtView.setText((CharSequence) getItem(position).getChildAt(1));

        //Button no = (Button) convertView.findViewById(R.id.no_button);
        //Button yes = (Button) convertView.findViewById(R.id.yes_button);

        return view;
    }
}
