package com.example.assignment3.controllers;

import com.example.assignment3.mappers.CharacterMapper;
import com.example.assignment3.mappers.MovieMapper;
import com.example.assignment3.model.Movie;
import com.example.assignment3.model.dto.character.CharacterDTO;
import com.example.assignment3.model.dto.franchise.FranchisePutDTO;
import com.example.assignment3.model.dto.movie.MovieDTO;
import com.example.assignment3.model.dto.movie.MoviePostDTO;
import com.example.assignment3.model.dto.movie.MoviePutDTO;
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
    private final CharacterMapper characterMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper, CharacterMapper characterMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
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
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
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
                                    schema = @Schema(implementation = MovieDTO.class))
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
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieDTO.class))
            )
    })
    public ResponseEntity deleteById(@RequestParam int id) {
        MovieDTO movieDTO = movieMapper.movieToMovieDto(
                movieService.findById(id)
        );
        movieService.deleteById(id);
        return ResponseEntity.ok(movieDTO);
    }

    /**
     * Calls update method from MovieService
     * @param entity New entity to replace old
     * @param id ID for path variable
     * @return Response entity based on response code
     */
    @PutMapping("{id}")
    @Operation(summary = "Update a movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    })
    public ResponseEntity update(@RequestBody MoviePutDTO entity, @PathVariable int id) {
        if (id != entity.getId()) {
            return ResponseEntity.badRequest().build();
        }
        movieService.update(movieMapper.moviePutDtoToMovie(entity));
        return ResponseEntity.noContent().build();
    }

    /**
     * Calls findCharactersInMovie from MovieService
     * @param id ID of movie
     * @return Response entity of all characters in given movie
     */
    @GetMapping("{id}/characters")
    @Operation(summary = "Get characters in movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CharacterDTO.class)))
                    }
            )
    })
    public ResponseEntity findCharactersInMovie(@PathVariable int id) {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDto(
                        movieService.findCharactersInMovie(id)
                )
        );
    }
}
