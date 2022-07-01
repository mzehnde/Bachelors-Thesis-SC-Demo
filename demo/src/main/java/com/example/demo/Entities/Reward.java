package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Reward")
public class Reward {
    @Id
    @Column(name = "id_reward")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Partner")
    private Integer partner;

    @Column(name = "Image")
    private String image;

    @Column(name = "Location")
    private String location;

    @Column(name = "qrcodereward")
    private String qrcodereward;

    @Column(name = "qrcodepartner")
    private String qrcodepartner;

    public Reward(String name, Integer Partner) {
        this.name = name;
        partner = Partner;
    }

    public Reward() {
    }

    public String getQRCodePartner() {
        return qrcodepartner;
    }

    public void setQRCodePartner(String QRCodePartner) {
        this.qrcodepartner = QRCodePartner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPartner() {
        return partner;
    }

    public void setPartnerId(Integer PartnerId) {
        partner = PartnerId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQRCodeReward() {
        return qrcodereward;
    }

    public void setQRCodeReward(String QRCodeReward) {
        this.qrcodereward = QRCodeReward;
    }
}
