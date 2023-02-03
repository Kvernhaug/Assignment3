package com.example.assignment3.services.movie;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Movie;
import com.example.assignment3.services.CrudService;

import java.util.Set;

/**
 * Service interface for performing actions on Movie entities.
 * Inherits generic CRUD actions form CrudService.
 */
public interface MovieService extends CrudService<Movie, Integer> {

    /**
     * Get all characters belonging to a given movie
     * @param movieId Movie ID
     * @return characters belonging to movie entity
     */
    Set<Character> findCharactersInMovie(int movieId);
}
