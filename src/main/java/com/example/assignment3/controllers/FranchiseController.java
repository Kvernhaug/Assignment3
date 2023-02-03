package com.example.assignment3.controllers;

import com.example.assignment3.mappers.CharacterMapper;
import com.example.assignment3.mappers.FranchiseMapper;
import com.example.assignment3.mappers.MovieMapper;
import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.dto.character.CharacterDTO;
import com.example.assignment3.model.dto.franchise.FranchiseDTO;
import com.example.assignment3.model.dto.franchise.FranchisePostDTO;
import com.example.assignment3.model.dto.franchise.FranchisePutDTO;
import com.example.assignment3.model.dto.movie.MovieDTO;
import com.example.assignment3.services.franchise.FranchiseService;
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
 * Controller for handling user interaction with Franchise entities.
 * The actions are performed by calling the relevant methods in FranchiseService.
 */
@RestController
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {
    private final FranchiseService franchiseService;
    private final FranchiseMapper franchiseMapper;
    private final MovieMapper movieMapper;
    private final CharacterMapper characterMapper;

    public FranchiseController(
            FranchiseService franchiseService,
            FranchiseMapper franchiseMapper,
            MovieMapper movieMapper,
            CharacterMapper characterMapper
    ) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }

    /**
     * Calls the findAll method from FranchiseService
     * @return Response entity of all franchises to swagger
     */
    @GetMapping
    @Operation(summary = "Get all franchises")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Franchise.class)))
                    }
            )
    })
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
                franchiseMapper.franchiseToFranchiseDto(
                        franchiseService.findAll()
                )
        );
    }

    /**
     * Calls findById method in FranchiseService
     * @param id ID of requested franchise
     * @return Response entity of requested franchise to swagger
     */
    @GetMapping("{id}")
    @Operation(summary = "Get franchise by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Franchise.class))
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
        return ResponseEntity.ok(
                franchiseMapper.franchiseToFranchiseDto(
                        franchiseService.findById(id)
                )
        );
    }

    /**
     * Calls add method from FranchiseService
     * @param entity FranchisePostDTO entity to add to movie table
     * @return Response entity showing the added franchise
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
    public ResponseEntity add(@RequestBody FranchisePostDTO entity) throws URISyntaxException {
        Franchise franchise = franchiseMapper.franchisePostDtoToFranchise(entity);
        franchiseService.add(franchise);
        URI uri = new URI("api/v1/frinchise/" + 1);
        return ResponseEntity.created(uri).build();
    }

    /**
     * Calls deleteById method from FranchiseService
     * @param id ID of franchise to delete
     * @return Response entity of deleted franchise to swagger
     */
    @DeleteMapping
    @Operation(summary = "Delete franchise by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Removed",
                    content = @Content
            )
    })
    public ResponseEntity deleteById(@RequestParam int id) {
        FranchiseDTO franchiseDTO = franchiseMapper.franchiseToFranchiseDto(
                franchiseService.findById(id)
        );
        franchiseService.deleteById(id);
        return ResponseEntity.ok(franchiseDTO);
    }

    /**
     * Calls update method from FranchiseService
     * @param entity New entity to replace old
     * @param id ID for path variable
     * @return Response entity based on response code
     */
    @PutMapping("{id}")
    @Operation(summary = "Update a franchise")
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
    public ResponseEntity update(@RequestBody FranchisePutDTO entity, @PathVariable int id) {
        if (id != entity.getId()) {
            return ResponseEntity.badRequest().build();
        }
        franchiseService.update(franchiseMapper.franchisePutDtoToFranchise(entity));
        return ResponseEntity.noContent().build();
    }

    /**
     * Calls findMoviesInFranchise from FranchiseService
     * @param id ID of franchise
     * @return Response entity of all movies in given franchise
     */
    @GetMapping("{id}/movies")
    @Operation(summary = "Get movies in franchise")
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
    public ResponseEntity findMoviesInFranchise(@PathVariable int id) {
        return ResponseEntity.ok(
                movieMapper.movieToMovieDto(
                        franchiseService.findMoviesInFranchise(id)
                )
        );
    }

    /**
     * Calls findCharactersInFranchise from FranchiseService
     * @param id ID of franchise
     * @return Response entity of all characters in given franchise
     */
    @GetMapping("{id}/characters")
    @Operation(summary = "Get characters in franchise")
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
    public ResponseEntity findCharactersInFranchise(@PathVariable int id) {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDto(
                        franchiseService.findCharactersInFranchise(id)
                )
        );
    }

    /**
     * Calls updateMovies from FranchiseService
     * @param id ID of franchise
     * @param movieIds Movie IDs
     * @return Response entity
     */
    @PutMapping("{id}/movies")
    @Operation(summary = "Update movies in franchise")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Success",
                    content = @Content
            )
    })
    public ResponseEntity updateMovies(@PathVariable int id, @RequestBody int[] movieIds) {
        franchiseService.updateMovies(id, movieIds);
        return ResponseEntity.noContent().build();
    }
}
