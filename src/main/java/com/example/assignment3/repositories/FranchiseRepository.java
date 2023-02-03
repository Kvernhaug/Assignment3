package com.example.assignment3.repositories;

import com.example.assignment3.model.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Simple composed repository interface, for franchise entity, that includes CrudRepository and PageableRepository.
 * Domain type  -> Franchise
 * ID type      -> Integer
 */
@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {

}
