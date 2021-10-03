package com.alkemy.java.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "organizations")
@SQLDelete(sql = "UPDATE organizations SET deleted = true WHERE id=?")
@Where(clause = " deleted = false ")
public class Organization {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organization")
    private Long id;

    @NotEmpty(message = "This field cannot be null or empty!")
    @Column(length = 30)
    private String name;

    @NotEmpty(message = "This field cannot be null or empty!")
    private String image;

    @Column(length = 30)
    private String address;

    @Column(length = 30)
    private String phone;

    @NotEmpty(message = "This field cannot be null or empty!")
    @Email
    @Column(name = "email", unique = true, length = 50)
    private String email;

    @NotEmpty(message = "This field cannot be null or empty!")
    @Column(name = "welcome_text")
    private String welcomeText;

    @Column(name = "about_us_text")
    private String aboutUsText;

    @Column(name = "create_date")
    private Date creationDate;

    @Column(name = "last_update")
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Slide> slides;

    private Boolean deleted;

    public Organization(String name, String image, String address, String phone, String email, String welcomeText, String aboutUsText) {
        this.name = name;
        this.image = image;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.welcomeText = welcomeText;
        this.aboutUsText = aboutUsText;
        this.creationDate = new Date();
        this.lastUpdate = this.creationDate;
        this.deleted = Boolean.FALSE;
    }
}
