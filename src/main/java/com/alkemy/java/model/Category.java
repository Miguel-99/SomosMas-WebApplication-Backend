package com.alkemy.java.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "categories")
@NoArgsConstructor @AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter @NotEmpty(message = "Field name should not be null or empty")
    private String name;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String image;

    @Getter @Setter
    private boolean deleted;

    @Getter @Setter @Column(name = "create_date")
    private Date createDate;

    @Getter @Setter @Column(name = "last_update")
    private Date lastUpdate;

}