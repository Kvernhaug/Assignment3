package com.example.assignment3.mappers;

import com.example.assignment3.model.Franchise;
import com.example.assignment3.model.Movie;
import com.example.assignment3.model.dto.franchise.FranchiseDTO;
import com.example.assignment3.model.dto.franchise.FranchisePostDTO;
import com.example.assignment3.model.dto.franchise.FranchisePutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for handling mapping between DTOs and internal objects for Franchise
 */
@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {

    /**
     * Convert Franchise to FranchiseDTO.
     * @param franchise Franchise entity to convert
     * @return FranchiseDTO entity
     */
    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds")
    public abstract FranchiseDTO franchiseToFranchiseDto(Franchise franchise);

    /**
     * Convert a collection of Franchise entities to a collection of FranchiseDTO entities.
     * @param franchises Franchise entities to convert
     * @return FranchiseDTO entities
     */
    public abstract Collection<FranchiseDTO> franchiseToFranchiseDto(Collection<Franchise> franchises);

    /**
     * Convert FranchisePostDTO to Franchise
     * @param franchisePostDTO FranchisePostDTO entity to convert
     * @return Franchise entity
     */
    public abstract Franchise franchisePostDtoToFranchise(FranchisePostDTO franchisePostDTO);

    /**
     * Convert FranchisePutDTO to Franchise
     * @param franchisePutDTO FranchisePutDTO entity to convert
     * @return Franchise entity
     */
    public abstract Franchise franchisePutDtoToFranchise(FranchisePutDTO franchisePutDTO);

    /**
     * Convert set of Movie entities to their respective IDs.
     * @param source Set of Movie entities
     * @return Set of movie IDs
     */
    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}
