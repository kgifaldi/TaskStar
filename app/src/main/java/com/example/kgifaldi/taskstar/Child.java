package com.example.kgifaldi.taskstar;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MaggieThomann on 5/5/17.
 */

public class Child implements Serializable{
    String id;
    String parent_id;
    String username;
    String reward_balance;
    ArrayList<RewardClass> rewards_purchased;
    ArrayList<RewardClass> rewards_available;
    ArrayList<String> task_list;
    String image_src;

    public Child(){
        // This has no parameters and is used to initialize a parent
    }

    //

    Child(String[] args, ArrayList<RewardClass> rewards_purchased, ArrayList<RewardClass> rewards_available){
        setParentId(args[0]);
        setId(args[1]);
        setUsername(args[2]);
        setRewardBalance(args[3]);

        setRewardsPurchased(rewards_purchased);

        setRewardsAvailable(rewards_available);


        ArrayList<String> task_list_set_up = null;
        for (String each_task : args[6].split(",")){
            task_list_set_up.add(each_task);
        }
        setTaskList(task_list_set_up);

        setImageSrc(args[7]);
    }

    public String getBalance(){ return reward_balance;}
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
        parent_id = id;
    }
    public String getParentId(){return parent_id;}

    public void setRewardBalance(String bal){
        reward_balance = bal;
    }
    public String getRewardBalance(){return reward_balance;}


    public ArrayList<RewardClass> getRewardsPurchased() { return rewards_purchased;}
    public void setRewardsPurchased(ArrayList<RewardClass> rewards) {
        rewards_purchased = rewards;
    }

    public ArrayList<RewardClass> getRewardsAvailable() { return rewards_available;}
    public void setRewardsAvailable(ArrayList<RewardClass> rewards) {
        rewards_available = rewards;
    }

    public ArrayList<String> getTaskList() { return task_list;}
    public void setTaskList(ArrayList<String> tasks) {
        task_list = tasks;
    }

    public String getImageSrc() {
        return image_src;
    }
    public void setImageSrc(String image_src) {
        this.image_src = image_src;
    }

    /*
    complete_task(String task_id)
    purchased_reward(String cost_of_reward)
    redeem_reward(String cost_of_reward)
     */

    public void parent_adding_reward(RewardClass reward_obj){
        String reward_id = reward_obj.getRewardId();
        rewards_available.add(reward_id);
    }

    /*
    purchased_reward(String cost_of_reward)
    redeem_reward(String Id_Reward)
    complete_task(String task_name)
     */

    public void purchased_reward(String[] rew){
        int i;
        int balance = Integer.parseInt(this.getRewardBalance().trim());
        if (balance >= Integer.parseInt(rew[1].trim())) {
            String[] temp_rews = new String[this.getRewardsPurchased().length + 1];
            for (i = 0; i < this.getRewardsPurchased().length; i++) {

                temp_rews[i] = this.getRewardsPurchased()[i];

            }
            //System.out.println("before adding new rew");
            temp_rews[PublicData.selected_child.getRewardsPurchased().length] = rew[0];
            this.setRewardsPurchased(temp_rews);
            this.setRewardBalance(Integer.toString(balance - Integer.parseInt(rew[1].trim())));
        }
    }

    public void redeem_reward(String rew){

    }
}
