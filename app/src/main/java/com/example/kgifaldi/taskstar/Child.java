package com.example.kgifaldi.taskstar;

import java.io.Serializable;

/**
 * Created by MaggieThomann on 5/5/17.
 */

/*
public static final String TABLE_CHILDREN = "children";
    public static final String CHILD_ID = "_id";
    public static final String CHILD_USER_NAME = "child_user_name";
    public static final String CHILD_REWARD_BALANCE = "child_reward_balance";
    public static final String REWARDS_PURCHASED_LIST = "rewards_purchased_list";
    public static final String REWARDS_AVAILABLE_LIST = "rewards_available_list";
    public static final String TASK_LIST = "task_list";
    public static final String CHILD_IMAGE_SRC = "image_src";

 */

public class Child implements Serializable{
    String id;
    String parent_id;
    String username;
    String reward_balance;
    String[] rewards_purchased;
    String[] rewards_available;
    String[] task_list;
    String image_src;

    public Child(){
        // This has no parameters and is used to initialize a parent
    }

    //

    Child(String[] args){
        setParentId(args[0]);
        setId(args[1]);
        setUsername(args[2]);
        setRewardBalance(args[3]);
        setRewardsPurchased(args[4].split(","));
        setRewardsAvailable(args[5].split(","));
        setTaskList(args[6].split(","));
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setParentId(String id) {
        this.id = parent_id;
    }
    public String getParentId(){return parent_id;}

    public void setRewardBalance(String bal){
        reward_balance = bal;
    }
    public String getRewardBalance(){return reward_balance;}


    public String[] getRewardsPurchased() { return rewards_purchased;}
    public void setRewardsPurchased(String[] rewards) {
        rewards_purchased = rewards;
    }

    public String[] getRewardsAvailable() { return rewards_available;}
    public void setRewardsAvailable(String[] rewards) {
        rewards_available = rewards;
    }

    public String[] getTaskList() { return task_list;}
    public void setTaskList(String[] tasks) {
        task_list = tasks;
    }

    public String getImageSrc() {
        return image_src;
    }

    public void setImageSrc(String image_src) {
        this.image_src = image_src;
    }

}
