package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name = "NormalRewardGivenOut")
public class NormalRewardGivenOut {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Image")
    private String Image;

    @Column(name = "Location")
    private String Location;

    public NormalRewardGivenOut(Long id, String name, String image, String location) {
        Id = id;
        Name = name;
        Image = image;
        Location = location;
    }

    public NormalRewardGivenOut() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}