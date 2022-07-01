package com.example.demo.Entities;

public class User {
    private String emailAddress;
    private String partner_QR_Code;

    public String getPartner_QR_Code() {
        return partner_QR_Code;
    }

    public void setPartner_QR_Code(String Partner) {
        partner_QR_Code = Partner;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public User(String emailAddress, String Partner) {
        this.emailAddress = emailAddress;
        this.partner_QR_Code = Partner;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
