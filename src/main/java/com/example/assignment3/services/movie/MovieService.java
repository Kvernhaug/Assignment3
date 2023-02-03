package com.example.assignment3.services.movie;

import com.example.assignment3.model.Movie;
import com.example.assignment3.services.CrudService;

/**
 * Service interface for performing actions on Movie entities.
 * Inherits generic CRUD actions form CrudService.
 */
public interface MovieService extends CrudService<Movie, Integer> {

}
