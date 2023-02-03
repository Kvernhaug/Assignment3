package com.example.assignment3.model.dto.franchise;


import lombok.Data;

/**
 * Franchise Data Transfer Object for post operations.
 * These fields are required when a new franchise is posted.
 */
@Data
public class FranchisePostDTO {
    private String name;
}
