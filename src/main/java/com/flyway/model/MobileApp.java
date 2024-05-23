package com.flyway.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MobileApp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int appId;

    private String appName;
    private String appType;

    @ManyToMany
    private List<User> users=new ArrayList<>();
}
