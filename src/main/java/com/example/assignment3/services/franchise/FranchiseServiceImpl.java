package com.example.assignment3.services.franchise;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.Movie;
import com.example.assignment3.repositories.FranchiseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Implementation of FranchiseService interface
@Service
public class FranchiseServiceImpl implements FranchiseService{
    /**
     * Most of the implemented methods relies on functionality
     * provided by the FranchiseRepository
     */
    private final FranchiseRepository franchiseRepository;
    private final Logger logger = LoggerFactory.getLogger(FranchiseServiceImpl.class);

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }

    @Override
    public Franchise findById(Integer id) {
        return franchiseRepository.findById(id).get();
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public Franchise update(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        franchiseRepository.deleteById(id);
    }

    @Override
    public void delete(Franchise entity) {
        franchiseRepository.delete(entity);
    }


    @Override
    public Set<Movie> findMoviesInFranchise(int franchiseId) {
        return franchiseRepository.findById(franchiseId).get().getMovies();
    }

    @Override
    public Set<Character> findCharactersInFranchise(int franchiseId) {
        Set<Character> characters = new HashSet<>();
        for (Movie movie : franchiseRepository.findById(franchiseId).get().getMovies()) {
            characters.addAll(movie.getCharacters());
        }
        return characters;
    }
}
