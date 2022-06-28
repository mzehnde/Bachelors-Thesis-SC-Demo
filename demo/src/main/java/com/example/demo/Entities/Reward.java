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
    private Integer partnerId;

    public Reward(String name, Integer PartnerId) {
        this.name = name;
        partnerId = PartnerId;
    }

    public Reward() {
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

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer PartnerId) {
        partnerId = PartnerId;
    }
}
