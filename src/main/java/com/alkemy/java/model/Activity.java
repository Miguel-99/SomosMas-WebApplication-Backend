package com.alkemy.java.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Activity implements Serializable {

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

    private Boolean delete;

}
