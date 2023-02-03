package com.example.assignment3.model.dto.character;

import lombok.Data;

/**
 * Character Data Transfer Object for post operations.
 * These fields are required when a new character is posted.
 */
@Data
public class CharacterPostDTO {
    private String name;
    private String gender;
}
