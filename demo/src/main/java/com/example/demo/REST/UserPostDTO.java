package com.example.demo.REST;

public class UserPostDTO {
    private String emailAddress;
    private String kindOfReward;

    public String getKindOfReward() {
        return kindOfReward;
    }
    public void setKindOfReward(String kindOfReward) {
        this.kindOfReward = kindOfReward;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
