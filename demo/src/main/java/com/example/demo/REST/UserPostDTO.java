package com.example.demo.REST;

public class UserPostDTO {
    private String emailAddress;
    private String partner_QR_Code;

    public String getPartner_QR_Code() {
        return partner_QR_Code;
    }
    public void setPartner_QR_Code(String partner) {
        partner_QR_Code = partner;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
