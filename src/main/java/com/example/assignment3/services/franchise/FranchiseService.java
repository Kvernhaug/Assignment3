package com.example.assignment3.services.franchise;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.Movie;
import com.example.assignment3.services.CrudService;

import java.util.Set;

/**
 * Service interface for performing actions on Franchise entities.
 * Inherits generic CRUD actions form CrudService.
 */
public interface FranchiseService extends CrudService<Franchise, Integer> {

    /**
     * Get all movies belonging to a given franchise ID
     * @param franchiseId Franchise ID
     * @return movies belonging to franchise entity
     */
    Set<Movie> findMoviesInFranchise(int franchiseId);

    /**
     * Get all characters belonging to a given franchise ID
     * @param franchiseId Franchise ID
     * @return characters belonging to franchise entity
     */
    Set<Character> findCharactersInFranchise(int franchiseId);

    /**
     * Update movies associated with given franchise.
     * Ignores previous movies in franchise.
     * @param franchiseID ID of franchise
     * @param movieIDs list of movie IDs
     */
    void updateMovies(int franchiseID, int[] movieIDs);
}
