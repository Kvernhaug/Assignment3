package com.example.assignment3.controllers;

import com.example.assignment3.mappers.FranchiseMapper;
import com.example.assignment3.mappers.MovieMapper;
import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.Movie;
import com.example.assignment3.model.dto.franchise.FranchiseDTO;
import com.example.assignment3.model.dto.franchise.FranchisePostDTO;
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

    public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
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
}
