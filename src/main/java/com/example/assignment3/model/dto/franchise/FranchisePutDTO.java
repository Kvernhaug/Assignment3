package com.example.assignment3.model.dto.franchise;

import lombok.Data;

/**
 * Franchise Data Transfer Object for put operations.
 * These fields are required when a franchise is updated.
 */
@Data
public class FranchisePutDTO {
    private int id;
    private String name;
}
