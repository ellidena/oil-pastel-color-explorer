package com.example.colorpicker.service;

import com.example.colorpicker.model.Color;
import com.example.colorpicker.repository.ColorRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColorServiceTest {

    // helper method that lets me create a service with any number of colors
    private ColorService createServiceWithColors(int count){
        ColorRepository repository = new ColorRepository();
        for (int i = 0; i < count; i++){
            repository.add(new Color("Color"+ i, i, "#000000"));
        }
        return new ColorService(repository);
    }

    @Test
    void alwaysReturnsFourGrouos(){
        ColorService service = createServiceWithColors(10);

        List<List<Color>> groups = service.groupColorsIntoFour();

        assertEquals(4, groups.size());
    }

    @Test
    void groupsAreEvenWhenDivisibleByFour(){
        ColorService service = createServiceWithColors(72);

        List<List<Color>> groups = service.groupColorsIntoFour();

        assertEquals(18, groups.get(0).size());
        assertEquals(18, groups.get(1).size());
        assertEquals(18, groups.get(2).size());
        assertEquals(18, groups.get(3).size());
    }

    @Test
    void leftOversGoIntoTheLastGroup(){
        ColorService service = createServiceWithColors(70);

        List<List<Color>> groups = service.groupColorsIntoFour();

        // groupSize = 70 / 4 = 17
        assertEquals(17, groups.get(0).size());
        assertEquals(17, groups.get(1).size());
        assertEquals(17, groups.get(2).size());

        // last group should contain all remaining colors
        int expectedLastGroupSize = 70 - (17*3);
        assertEquals(expectedLastGroupSize, groups.get(3).size());
    }

    @Test
    void tinyListStillMakesFourGroups(){
        ColorService service = createServiceWithColors(3);

        List<List<Color>> groups = service.groupColorsIntoFour();

        assertEquals(4, groups.size());
        assertEquals(1, groups.get(0).size());
        assertEquals(1, groups.get(1).size());
        assertEquals(1, groups.get(2).size());
        assertEquals(0, groups.get(3).size()); // empty list
    }

}