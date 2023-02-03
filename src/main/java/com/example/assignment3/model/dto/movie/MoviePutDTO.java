package com.example.assignment3.model.dto.movie;

import lombok.Data;

/**
 * Movie Data Transfer Object for put operations.
 * These fields are required when a movie is updated.
 */
@Data
public class MoviePutDTO {
    private int id;
    private String title;
    private int releaseYear;
    private String director;
}
