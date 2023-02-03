package com.example.assignment3.model.dto.character;

import lombok.Data;

import java.util.Set;

/**
 * Character Data Transfer Object for get operations.
 * These fields are shown to the user when performing get operations.
 */
@Data
public class CharacterDTO {
    private int id;
    private String name;
    private String alias;
    private String gender;
    private String picture;
    private Set<Integer> movies;
}
