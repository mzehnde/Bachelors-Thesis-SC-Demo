package com.example.demo.Entities;

import javax.persistence.*;

@Entity
public class Partner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;

    private String Name;

    private String QR_Code_Path;

    public Partner() { }


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

    public String getQR_path() {
        return QR_Code_Path;
    }

    public void setQR_path(String QR_path) {
        this.QR_Code_Path = QR_path;
    }

    public Partner(Long id, String name, String QR_path) {
        this.Id = id;
        this.Name = name;
        this.QR_Code_Path = QR_path;
    }
}
