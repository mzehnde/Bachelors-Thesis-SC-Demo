package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name = "NormalReward")
public class NormalReward {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Image")
    private String Image;

    @Column(name = "Location")
    private String Location;

    @Column(name = "Partner")
    private String Partner;



    public NormalReward(String name) {
        this.Name = name;
    }

    public NormalReward() {
    }

    public String getPartner() {
        return Partner;
    }

    public void setPartner(String partner) {
        Partner = partner;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }


}
