package com.example.assignment3.services.movie;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Movie;
import com.example.assignment3.repositories.CharacterRepository;
import com.example.assignment3.repositories.MovieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// Implementation of MovieService interface
@Service
public class MovieServiceImpl implements MovieService {
    /**
     * Most of the implemented methods relies on functionality
     * provided by the MovieRepository
     */
    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;
    private final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    public MovieServiceImpl(MovieRepository movieRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void delete(Movie entity) {
        movieRepository.delete(entity);
    }

    @Override
    public Set<Character> findCharactersInMovie(int movieId) {
        return movieRepository.findById(movieId).get().getCharacters();
    }

    @Override
    public void updateCharacters(int movieID, int[] characterIDs) {
        Movie movie = movieRepository.findById(movieID).get();
        Set<Character> characterList = new HashSet<>();

        for (int id: characterIDs) {
            characterList.add(characterRepository.findById(id).get());
        }
        movie.setCharacters(characterList);
        movieRepository.save(movie);
    }

}

