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
    ArrayList<TaskClass> task_list;
    String image_src;

    public Child(){
        // This has no parameters and is used to initialize a parent
    }

    //

    Child(String[] args, ArrayList<RewardClass> rewards_purchased, ArrayList<RewardClass> rewards_available, ArrayList<TaskClass> tasks_array, String image_src){
        setParentId(args[0]);
        setId(args[1]);
        setUsername(args[2]);
        setRewardBalance(args[3]);

        setRewardsPurchased(rewards_purchased);

        setRewardsAvailable(rewards_available);
        setTaskList(tasks_array);

        setImageSrc(image_src);
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

    public ArrayList<TaskClass> getTaskList() { return task_list;}
    public void setTaskList(ArrayList<TaskClass> tasks) {
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
        rewards_available.add(reward_obj);
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

            for (RewardClass r : this.getRewardsAvailable()){
                if (r.getRewardName() == rew[0]){
                    ArrayList<RewardClass> temp_rews = this.getRewardsPurchased();
                    temp_rews.add(r);
                    this.setRewardBalance(Integer.toString(balance - Integer.parseInt(r.getRewardPrice().trim())));
                    break;
                }
            }
        }
    }

    public void redeem_reward(String rew){

    }
}
