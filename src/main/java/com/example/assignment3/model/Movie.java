package com.example.assignment3.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 20)
    private String genre;
    private int releaseYear;
    @Column(length = 50)
    private String director;
    @Column(length = 50)
    private String picture;
    @Column(length = 50)
    private String trailer;
    // Relationships
    @ManyToMany
    private Set<Character> characters;
    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
}
