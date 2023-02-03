package com.example.assignment3.controllers;

import com.example.assignment3.mappers.MovieMapper;
import com.example.assignment3.model.Movie;
import com.example.assignment3.model.dto.movie.MovieDTO;
import com.example.assignment3.model.dto.movie.MoviePostDTO;
import com.example.assignment3.services.movie.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Controller for handling user interaction with Movie entities.
 * The actions are performed by calling the relevant methods in MovieService.
 */
@RestController
@RequestMapping(path = "api/v1/movies") // localhost:8080/api/v1/movies
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    /**
     * Calls the findAll method from MovieService
     * @return Response entity of all movies to swagger
     */
    @GetMapping
    @Operation(summary = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Movie.class)))
                    }
            )
    })
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
                movieMapper.movieToMovieDto(
                        movieService.findAll()
                )
        );
    }

    /**
     * Calls findById method in MovieService
     * @param id ID of requested movie
     * @return Response entity of requested movie to swagger
     */
    @GetMapping("{id}") // localhost:8080/api/v1/movies/1
    @Operation(summary = "Get movie by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Movie.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity findById(@PathVariable int id) {
        MovieDTO movie = movieMapper.movieToMovieDto(movieService.findById(id));
        return ResponseEntity.ok(movie);
    }

    /**
     * Calls add method from MovieService
     * @param entity MoviePostDTO entity to add to movie table
     * @return Response entity showing the added movie
     * @throws URISyntaxException throws exception if URI reference could not be parsed
     */
    @PostMapping
    @Operation(summary = "Adds a new movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity add(@RequestBody MoviePostDTO entity) throws URISyntaxException {
        Movie movie = movieMapper.moviePostDtoToMovie(entity);
        movieService.add(movie);
        URI uri = new URI("api/v1/movies/" + 1);
        return ResponseEntity.created(uri).build();
    }

    /**
     * Calls deleteById method from MovieService
     * @param id ID of movie to delete
     * @return Response entity of deleted movie to swagger
     */
    @DeleteMapping
    @Operation(summary = "Delete movie by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Removed",
                    content = @Content
            )
    })
    public ResponseEntity deleteById(@RequestParam int id) {
        MovieDTO movieDTO = movieMapper.movieToMovieDto(
                movieService.findById(id)
        );
        movieService.deleteById(id);
        return ResponseEntity.ok(movieDTO);
    }
}
