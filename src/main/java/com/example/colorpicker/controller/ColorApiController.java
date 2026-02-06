package com.example.colorpicker.controller;

import com.example.colorpicker.model.Color;
import com.example.colorpicker.service.ColorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class ColorApiController {
    private final ColorService service;

    public ColorApiController(ColorService service){
        this.service = service;
    }

    @GetMapping("/api/colors")
    public Collection<Color> getAllColors(){
        return service.getAllColors();
    }

    @GetMapping("/api/colors/in-order")
    public Collection<Color> getAllColorsInOrder(){
        return service.getAllInOrderByValue();
    }

    @GetMapping("/api/colors/random-four")
    public List<Color> getRandomFour(){
        return service.getRandomFourByValueGroups();
    }

}
