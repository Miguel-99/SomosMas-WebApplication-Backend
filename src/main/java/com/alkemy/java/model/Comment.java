package com.alkemy.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alkemy.java.model.User;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotEmpty(message = "Field body should not be empty")
    @Max(value = 255, message = "You have exceeded the number of characters available (255 max)")
    private String body;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_date", updatable = false, nullable = false)
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_update", nullable = false)
    private Date lastUpdate;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

}
