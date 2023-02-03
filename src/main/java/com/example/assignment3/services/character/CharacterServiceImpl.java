package com.example.assignment3.services.character;

import com.example.assignment3.model.Character;
import com.example.assignment3.repositories.CharacterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

// Implementation of CharacterService interface
@Service
public class CharacterServiceImpl implements CharacterService {
    /**
     * Most of the implemented methods relies on functionality
     * provided by the CharacterRepository
     */
    private final CharacterRepository characterRepository;
    private final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }


    @Override
    public Character findById(Integer id) {
        return characterRepository.findById(id).get();
    }

    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public Character add(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public Character update(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        characterRepository.deleteById(id);
    }

    @Override
    public void delete(Character entity) {
        characterRepository.delete(entity);
    }
}
