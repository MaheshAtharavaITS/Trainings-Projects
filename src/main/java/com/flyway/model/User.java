package com.flyway.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="User_Details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private long mono;
}
