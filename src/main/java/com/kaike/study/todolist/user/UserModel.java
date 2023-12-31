package com.kaike.study.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data // Getters e Setters do Lombok
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true) // Define que não será possível criar username duplicados
    public String username;
    public String name;
    public String password;
    @Column(unique = true) // Define que não será possível criar e-mails duplicados
    public String mail;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
