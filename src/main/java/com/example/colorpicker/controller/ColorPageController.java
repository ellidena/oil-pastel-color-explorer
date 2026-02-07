package com.example.colorpicker.controller;

import com.example.colorpicker.model.Color;
import com.example.colorpicker.service.ColorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ColorPageController {

    private final ColorService service;

    public ColorPageController(ColorService service){
        this.service = service;
    }

    @GetMapping("/")
    public String showHomePage(){
        return "home";
    }

    @GetMapping("/colors")
    public String showColorsPage(Model model){
        model.addAttribute("groups", service.groupColorsIntoFour());
        return "colors";
    }

    @GetMapping("/random-four")
    public String showRandomFourPage(Model model){
        model.addAttribute("picks", service.getRandomFourByValueGroups());
        return "random-four";
    }

    @GetMapping("/color/{number}")
    public String showColor(@PathVariable int number, Model model){
        Color color = service.getByNumber(number);
        model.addAttribute("color", color);
        return "color-detail";
    }
}
