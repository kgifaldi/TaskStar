package com.example.kgifaldi.taskstar;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Luigi on 5/5/17.
 */

public class Parent implements Serializable {
    String id;
    String username;
    String[] child_ids;
    String image_src;

    public Parent(){
        // This has no parameters and is used to initialize a parent
    }

    Parent(String[] Info){
        setId(Info[0]);
        setUsername(Info[1]);
        setChild_ids((Info[2].split(" ")));
        setImage_src(Info[3]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getChild_ids() {
        return child_ids;
    }

    public void setChild_ids(String[] child_ids) {
        this.child_ids = child_ids;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public void add_reward(RewardClass reward_obj, ArrayList<Child> children_obj_list){
        // Update all the children to have that reward available in the reward store
        for (Child each_child : children_obj_list){
            each_child.parent_adding_reward(reward_obj);
            System.out.println(each_child.getId());
        }

    }

    public void add_child(Child child_obj){
        PublicData.children_list.add(child_obj);
        PublicData.new_children.add(child_obj);
    }

    public void add_task(TaskClass task_obj, ArrayList<String> children_selected){
        ArrayList<TaskClass> temp_tasklist = null;
        for(Child child : PublicData.children_list){
            for (String child_selected : children_selected){
                System.out.println("Processing child selected: " + child_selected);
                if (child_selected.trim().equals(child.getId().trim())){
                    temp_tasklist = child.getTaskList();

                    System.out.println("Adding task = " + task_obj.getName());
                    temp_tasklist.add(task_obj);

                    child.setTaskList(temp_tasklist);
                }
            }

        }
    }

}
