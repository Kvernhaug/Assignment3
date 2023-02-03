package com.example.assignment3.mappers;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Movie;
import com.example.assignment3.model.dto.movie.MovieDTO;
import com.example.assignment3.model.dto.movie.MoviePostDTO;
import com.example.assignment3.model.dto.movie.MoviePutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for handling mapping between DTOs and internal objects for Movie
 */
@Mapper(componentModel = "spring")
public abstract class MovieMapper {

    /**
     * Convert Movie to MovieDTO.
     * @param movie Movie entity to convert
     * @return MovieDTO entity
     */
    @Mapping(target = "franchise", source = "franchise.id") // Convert Franchise entity to its ID value
    @Mapping(target = "characters", source = "characters", qualifiedByName = "charactersToIds") // Call Character to Character IDs converter
    public abstract MovieDTO movieToMovieDto(Movie movie);

    /**
     * Convert a collection of Movie entities to a collection of MovieDTO entities.
     * @param movies Movie entities to convert
     * @return MovieDTO entities
     */
    public abstract Collection<MovieDTO> movieToMovieDto(Collection<Movie> movies);

    /**
     * Convert MoviePostDTO to Movie
     * @param moviePostDTO MoviePostDTO entity to convert
     * @return Movie entity
     */
    public abstract Movie moviePostDtoToMovie(MoviePostDTO moviePostDTO);

    /**
     * Convert MoviePutDTO to Movie
     * @param moviePutDTO MoviePutDTO entity to convert
     * @return Movie entity
     */
    public abstract Movie moviePutDtoToMovie(MoviePutDTO moviePutDTO);

    /**
     * Convert set of Character entities to their respective IDs.
     * @param source Set of Character entities
     * @return Set of Character IDs
     */
    @Named("charactersToIds")
    Set<Integer> map(Set<Character> source) {
        if(source == null)
            return null;
        return source.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }
}
