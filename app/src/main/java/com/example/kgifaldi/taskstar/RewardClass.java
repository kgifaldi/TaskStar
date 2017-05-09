package com.example.kgifaldi.taskstar;

import java.io.Serializable;

/**
 * Created by MaggieThomann on 5/9/17.
 */

public class RewardClass implements Serializable {
    String reward_id;
    String reward_name;
    String price;
    String [] children_ids;

    public RewardClass() {
        // This has no parameters and is used to initialize a reward
    }

    RewardClass(String[] Info) {
        setRewardId(Info[0]);
        setRewardName(Info[1]);
        setRewardPrice(Info[2]);
        setRewardChildrenIds(Info[3].split(" "));
    }

    /* ID functions */
    public String getRewardId() {
        return reward_id;
    }

    public void setRewardId(String id) {
        this.reward_id = id;
    }


    /* Reward Name functions */

    public String getRewardName() {
        return reward_name;
    }

    public void setRewardName(String name) {
        this.reward_name = name;
    }

    /* Reward Price functions */

    public String getRewardPrice() {
        return price;
    }

    public void setRewardPrice(String price) {
        this.price = price;
    }

    /* Children ID Functions */
    public String getRewardChildrenIds() {
        return price;
    }

    public void setRewardChildrenIds(String [] ids) {
        this.children_ids = ids;
    }
}