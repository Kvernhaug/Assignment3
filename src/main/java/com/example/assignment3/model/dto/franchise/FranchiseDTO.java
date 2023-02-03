package com.example.assignment3.model.dto.franchise;

import lombok.Data;

import java.util.Set;

/**
 * Franchise Data Transfer Object for get operations.
 * These fields are shown to the user when performing get operations.
 */
@Data
public class FranchiseDTO {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movies;
}
