package com.example.twitter.clone.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tweet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Date createdAt;
}