package com.example.assignment3.repositories;

import com.example.assignment3.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Simple composed repository interface, for movie entity, that includes CrudRepository and PageableRepository.
 * Domain type  -> Movie
 * ID type      -> Integer
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}
