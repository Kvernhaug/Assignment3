package com.example.assignment3.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    private String description;
    // Relationships
    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;

}
