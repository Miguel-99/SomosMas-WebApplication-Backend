package com.alkemy.java.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "slides")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String imageUrl;

    @NotBlank
    private String text;

    @NotBlank
    @Column(name = "orde")
    private String order;

    private Date creationDate;

    private Date lastUpdate;

    private Boolean deleted;

}
