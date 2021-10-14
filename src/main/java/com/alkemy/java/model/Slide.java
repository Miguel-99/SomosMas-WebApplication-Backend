package com.alkemy.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor  @AllArgsConstructor
@Table(name = "slides")
@SQLDelete(sql = "UPDATE slides SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Slide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Field ImageUrl should not be null or empty")
    private String imageUrl;

    @NotBlank(message = "Field Text should not be null or empty")
    private String text;

    @NotNull(message = "Field NumberOrder should not be null or empty")
    @Column(name = "number_order")
    private int numberOrder;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private Organization organizationId;

    private boolean deleted = Boolean.FALSE;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "last_update")
    private Date lastUpdate;

}
