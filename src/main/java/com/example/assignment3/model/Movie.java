package com.example.assignment3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * This is a class representation of the movie table in the database.
 * Each field variable represents a column in the table.
 */
@Entity
@Getter
@Setter
public class Movie {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 20)
    private String genre;
    @Column(nullable = false)
    private int releaseYear;
    @Column(length = 50, nullable = false)
    private String director;
    @Column(length = 50)
    private String picture;
    @Column(length = 50)
    private String trailer;
    // Relationships
    /**
     * Many-to-many relationship with the characters table
     * Relationship owned by the movie table.
     */
    @ManyToMany
    private Set<Character> characters;
    /**
     * Many-to-one relationship with the franchise table.
     * Relationship owned by the movie table.
     */
    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;
}
