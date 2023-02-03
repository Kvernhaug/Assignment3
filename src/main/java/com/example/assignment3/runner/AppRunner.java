package com.example.assignment3.runner;

import com.example.assignment3.services.movie.MovieService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// TODO: Used for manual debugging, should not be in final version.
@Component
public class AppRunner implements ApplicationRunner {
    private final MovieService movieService;

    public AppRunner(MovieService movieService) {
        this.movieService = movieService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
