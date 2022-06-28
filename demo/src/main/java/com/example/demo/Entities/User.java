package com.example.demo.Entities;

public class User {
    private String emailAddress;
    private Long partner;

    public Long getPartner() {
        return partner;
    }

    public void setPartner(Long Partner) {
        partner = Partner;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public User(String emailAddress, Long Partner) {
        this.emailAddress = emailAddress;
        this.partner = Partner;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
