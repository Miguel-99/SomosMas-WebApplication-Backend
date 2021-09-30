package com.alkemy.java.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@Table(name = "testimonials")
@SQLDelete(sql = "UPDATE categories SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Testimonial{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Field name should not be null or empty")
    private String name;
    private String image;
    private String content;
    @Column(name = "active")
    private Boolean deleted = Boolean.FALSE;
    @Column(name="create_date")
    private Date createDate;
    @Column(name="last_update")
    private Date updateDate;

}
