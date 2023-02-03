package com.example.assignment3.model.dto.movie;

import lombok.Data;

/**
 * Movie Data Transfer Object for post operations.
 * These fields are required when a new movie is posted.
 */
@Data
public class MoviePostDTO {
    private String title;
    private int releaseYear;
    private String director;
}
