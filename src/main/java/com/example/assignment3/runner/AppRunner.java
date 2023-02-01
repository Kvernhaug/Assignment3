package com.example.assignment3.runner;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Movie;
import com.example.assignment3.repositories.CharacterRepository;
import com.example.assignment3.services.movie.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Component
public class AppRunner implements CommandLineRunner {

    private final MovieService movieService;

    public AppRunner(MovieService movieService) {
        this.movieService = movieService;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Movie movie = movieService.findById(1);
        System.out.println(movie.getTitle());
    }


}

