package com.example.assignment3.services.movie;

import com.example.assignment3.model.Movie;
import com.example.assignment3.repositories.MovieRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

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

}

