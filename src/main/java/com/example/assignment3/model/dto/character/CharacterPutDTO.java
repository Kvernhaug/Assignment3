package com.example.assignment3.model.dto.character;

import lombok.Data;

/**
 * Character Data Transfer Object for put operations.
 * These fields are required when a character is updated.
 */
@Data
public class CharacterPutDTO {
    private int id;
    private String name;
    private String gender;
}
