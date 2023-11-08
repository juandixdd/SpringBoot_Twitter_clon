package com.example.twitter.clone.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "El id del usuario no puede ser nulo")
    private Long userId;

    @Column(name = "content")
    @NotBlank(message = "Debe ingresar el contenido del tweet")
    @NotNull(message = "El contenido del tweet no puede ser nulo")
    private String content;

    @Column(name = "created_at")
    private Date createdAt;
}