package com.example.assignment3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * This is a class representation of the franchise table in the database.
 * Each field variable represents a column in the table.
 */
@Entity
@Getter
@Setter
public class Franchise {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    private String description;
    // Relationships
    /**
     * One-to-many relationship with the movie table.
     * Relationship owned by movie table.
     */
    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;

}
