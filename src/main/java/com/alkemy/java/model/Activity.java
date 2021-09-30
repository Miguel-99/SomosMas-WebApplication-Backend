package com.alkemy.java.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
@Table (name = "activities")
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id=?")
@Where(clause = " deleted = false ")
public class Activity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field name should not be null or empty")
    private String name;

    @Lob
    @NotBlank (message = "Field content should not be null or empty")
    private String content;

    @NotBlank(message = "Field image should not be null or empty")
    private String image;

    @Temporal(TemporalType.DATE)
    @Column (name = "create_date")
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column (name = "update_date")
    private Date updateDate;

    private Boolean delete = Boolean.FALSE;

}
