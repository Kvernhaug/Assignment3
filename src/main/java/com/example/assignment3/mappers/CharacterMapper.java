package com.example.assignment3.mappers;

import com.example.assignment3.model.Character;
import com.example.assignment3.model.Movie;
import com.example.assignment3.model.dto.character.CharacterDTO;
import com.example.assignment3.model.dto.character.CharacterPostDTO;
import com.example.assignment3.model.dto.character.CharacterPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for handling mapping between DTOs and internal objects for Character
 */
@Mapper(componentModel = "spring")
public abstract class CharacterMapper {

    /**
     * Convert Character to CharacterDTO.
     * @param character Character entity to convert
     * @return CharacterDTO entity
     */
    @Mapping(target = "movies", source = "movies", qualifiedByName = "moviesToIds") // Call Movies to Movie IDs converter
    public abstract CharacterDTO characterToCharacterDto(Character character);

    /**
     * Convert a collection of Character entities to a collection of CharacterDTO entities.
     * @param characters Character entities to convert
     * @return CharacterDTO entities
     */
    public abstract Collection<CharacterDTO> characterToCharacterDto(Collection<Character> characters);

    /**
     * Convert CharacterPostDTO to Character
     * @param characterPostDTO CharacterPostDTO entity to convert
     * @return Character entity
     */
    public abstract Character characterPostDtoToCharacter(CharacterPostDTO characterPostDTO);

    /**
     * Convert CharacterPutDTO to Character
     * @param characterPutDTO CharacterPutDTO entity to convert
     * @return Character entity
     */
    public abstract Character characterPutDtoToCharacter(CharacterPutDTO characterPutDTO);

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
