package com.alkemy.java.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity
@Table(name = "Members")
public class Member{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_member")
    private long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "image")
    @NotNull
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "created_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "soft_delete")
    private boolean softDelete;

    @PrePersist
    public void prePersist(){
        this.createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        this.updateDate = new Date();
    }
}
