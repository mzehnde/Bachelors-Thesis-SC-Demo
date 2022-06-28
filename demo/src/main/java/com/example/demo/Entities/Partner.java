package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Partner")
public class Partner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String Name;

    private Integer QR_Code_Path;

    public Partner() { }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getQR_path() {
        return QR_Code_Path;
    }

    public void setQR_path(Integer QR_path) {
        this.QR_Code_Path = QR_path;
    }

    public Partner(String name, Integer QR_path) {
        this.Name = name;
        this.QR_Code_Path = QR_path;
    }
}
