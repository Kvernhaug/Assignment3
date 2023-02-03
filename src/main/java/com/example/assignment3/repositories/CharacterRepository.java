package com.example.assignment3.repositories;


import com.example.assignment3.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Simple composed repository interface, for character entity, that includes CrudRepository and PageableRepository.
 * Domain type  -> Character
 * ID type      -> Integer
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {

}
