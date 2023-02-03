package com.example.assignment3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


/**
 * This is a class representation of the character table in the database.
 * Each field variable represents a column in the table.
 */
@Entity
@Getter
@Setter
public class Character {
    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50)
    private String alias;
    @Column(length = 20, nullable = false)
    private String gender;
    @Column(length = 50)
    private String picture;
    // Relationships
    /**
     * Many-to-many relationship with the movie table.
     * movie is the owner of this relationship
     */
    @ManyToMany(mappedBy = "characters")
    private Set<Movie> movies;
}
