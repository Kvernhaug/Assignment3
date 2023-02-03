package com.example.assignment3.controllers;

import com.example.assignment3.mappers.CharacterMapper;
import com.example.assignment3.model.Character;
import com.example.assignment3.model.dto.character.CharacterDTO;
import com.example.assignment3.model.dto.character.CharacterPostDTO;
import com.example.assignment3.model.dto.character.CharacterPutDTO;
import com.example.assignment3.model.dto.franchise.FranchisePutDTO;
import com.example.assignment3.services.character.CharacterService;
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
 * Controller for handling user interaction with Character entities.
 * The actions are performed by calling the relevant methods in CharacterService.
 */
@RestController
@RequestMapping(path = "api/v1/character")
public class CharacterController {
    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    /**
     * Calls the findAll method from CharacterService
     * @return Response entity of all characters to swagger
     */
    @GetMapping // Mapping for get operations
    @Operation(summary = "Get all characters")  // Summary of operation, presented in swagger
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Character.class)))
                    }
            )
    })
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
                characterMapper.characterToCharacterDto(
                        characterService.findAll()
                )
        );
    }

    /**
     * Calls findById method in CharacterService
     * @param id ID of requested character
     * @return Response entity of requested character to swagger
     */
    @GetMapping("{id}")
    @Operation(summary = "Get character by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Character.class))
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
        CharacterDTO characterDTO = characterMapper.characterToCharacterDto(
                characterService.findById(id)
        );
        return ResponseEntity.ok(characterDTO);
    }

    /**
     * Calls add method from CharacterService
     * @param entity CharacterPostDTO entity to add to character table
     * @return Response entity showing the added character
     * @throws URISyntaxException throws exception if URI reference could not be parsed
     */
    @PostMapping
    @Operation(summary = "Add a new character")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity add(@RequestBody CharacterPostDTO entity) throws URISyntaxException {
        Character character = characterMapper.characterPostDtoToCharacter(entity);
        characterService.add(character);
        URI uri = new URI("api/v1/character/" + 1);
        return ResponseEntity.created(uri).build();
    }

    /**
     * Calls deleteById method from CharacterService
     * @param id ID of character to delete
     * @return Response entity of deleted character to swagger
     */
    @DeleteMapping
    @Operation(summary = "Delete character by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Removed",
                    content = @Content
            )
    })
    public ResponseEntity deleteById(@RequestParam int id) {
        CharacterDTO characterDTO = characterMapper.characterToCharacterDto(
                characterService.findById(id)
        );
        characterService.deleteById(id);
        return ResponseEntity.ok(characterDTO);
    }

    /**
     * Calls update method from CharacterService
     * @param entity New entity to replace old
     * @param id ID for path variable
     * @return Response entity based on response code
     */
    @PutMapping("{id}")
    @Operation(summary = "Update a character")
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
    public ResponseEntity update(@RequestBody CharacterPutDTO entity, @PathVariable int id) {
        if (id != entity.getId()) {
            return ResponseEntity.badRequest().build();
        }
        characterService.update(characterMapper.characterPutDtoToCharacter(entity));
        return ResponseEntity.noContent().build();
    }
}
