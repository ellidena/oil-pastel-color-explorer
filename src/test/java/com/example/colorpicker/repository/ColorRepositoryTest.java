package com.example.colorpicker.repository;

import com.example.colorpicker.model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.FactoryConfigurationError;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ColorRepositoryTest {
    ColorRepository repo;

    @BeforeEach
    void setUp() {
        repo = new ColorRepository();
        repo.loadFromCsv("/test-colors.csv");
    }

    @Test
    void loadFromCsv_LoadsTwoColors() {
        assertEquals(2, repo.getAll().size());
    }

    @Test
    void loadFromCSV_LoadsWhite(){
        Color white = repo.getByName("white");
        assertNotNull(white);
        assertEquals(1, white.getNumber());
    }

    @Test
    void loadFromCSV_LoadsBlack(){
        Color black = repo.getByNumber(2);
        assertNotNull(black);
        assertEquals("black", black.getName());
    }
}