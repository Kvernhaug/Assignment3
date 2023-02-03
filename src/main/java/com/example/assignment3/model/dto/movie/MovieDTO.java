package com.example.assignment3.model.dto.movie;

import lombok.Data;

import java.util.Set;

/**
 * Movie Data Transfer Object for get operations.
 * These fields are shown to the user when performing get operations.
 */
@Data // contains getter, setter, toString, equals, getHashCode
public class MovieDTO {
    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String picture;
    private String trailer;
    private int franchise;
    private Set<Integer> characters;

}
