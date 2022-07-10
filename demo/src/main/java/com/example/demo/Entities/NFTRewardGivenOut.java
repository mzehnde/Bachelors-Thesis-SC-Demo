package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name = "NFTRewardGivenOut")
public class NFTRewardGivenOut {

    @Id
    @Column(name = "Id")
    private int Id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Image")
    private String Image;

    @Column(name = "IfpsHash")
    private String IfpsHash;

    @Column(name = "Location")
    private String Location;

    public NFTRewardGivenOut(int id, String name, String image, String ifpsHash, String location) {
        Id = id;
        Name = name;
        Image = image;
        IfpsHash = ifpsHash;
        Location = location;
    }

    public NFTRewardGivenOut() {
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getIfpsHash() {
        return IfpsHash;
    }

    public void setIfpsHash(String ifpsHash) {
        IfpsHash = ifpsHash;
    }
}