package com.alkemy.java.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "organizations")
public class Organization {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "This field cannot be null or empty!")
    private String name;
    @NotEmpty(message = "This field cannot be null or empty!")
    private String image;
    private String address;
    private int phone;
    @NotEmpty(message = "This field cannot be null or empty!")
    @Email
    @Column(name = "email", unique = true)
    private String email;
    @NotEmpty(message = "This field cannot be null or empty!")
    private String welcomeText;
    private String aboutUsText;
    private Date creationDate;
    private Date lastUpdate;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Slide> slides; // coordine con mi compañero para que esto funcione
    private Boolean deleted;

    public Organization(String name, String image, String address, int phone, String email, String welcomeText, String aboutUsText, Boolean deleted) {
        this.name = name;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.welcomeText = welcomeText;
        this.aboutUsText = aboutUsText;
        this.creationDate = new Date();
        this.lastUpdate = this.creationDate;
        this.deleted = deleted;
    }
}
