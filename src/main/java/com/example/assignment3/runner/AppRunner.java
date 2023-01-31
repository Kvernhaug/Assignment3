package com.example.assignment3.runner;

import com.example.assignment3.model.Character;
import com.example.assignment3.repositories.CharacterRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AppRunner implements CommandLineRunner {

    private final CharacterRepository characterRepository;

    public AppRunner(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        printMoviesForCharacter(1);

    }

    public void findByName(String name) {
        Optional<Character> characters = characterRepository.findByName(name);
    }

    public void printMoviesForCharacter(int id) {
        Character character = characterRepository.findById(id).get();
        character.getMovies().forEach(System.out::println);
    }
}