package com.alkemy.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id_user=?")
@Where(clause = "deleted=false")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @NotNull(message = "This field cannot be null or empty!")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "This field cannot be null or empty!")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "This field cannot be null or empty!")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "This field cannot be null or empty!")
    @Column(name = "password")
    private String password;

    @Column(name = "photo")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", referencedColumnName = "id_role") //No tengo el codigo de mi compañero
    private Role role;                                              //Igualmente me puse en contacto con el para que nos pongamos
                                                                    // de acuerdo y tengamos la relacion de manera correcto
    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "last_update")
    private Date lastUpdate;




}
