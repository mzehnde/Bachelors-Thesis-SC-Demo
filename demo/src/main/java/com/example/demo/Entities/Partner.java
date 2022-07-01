package com.example.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name = "Partner")
public class Partner {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column (name = "Name")
    private String Name;

    @Column (name = "QRCode")
    private Integer QRCode;

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
        return QRCode;
    }

    public void setQR_path(Integer QRCode) {
        this.QRCode = QRCode;
    }

    public Partner(String name, Integer QRCode) {
        this.Name = name;
        this.QRCode = QRCode;
    }
}
