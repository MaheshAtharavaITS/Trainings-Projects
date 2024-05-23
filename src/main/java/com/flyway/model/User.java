package com.flyway.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="User_Details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;
    private String userName;
    private String userEmail;
    private long userMono;

    @ManyToMany
    private List<MobileApp> mobileApps=new ArrayList<>();
}
