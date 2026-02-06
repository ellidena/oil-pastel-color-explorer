package com.example.colorpicker.controller;

import com.example.colorpicker.model.Color;
import com.example.colorpicker.service.ColorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        // computing the color groups so Thymeleaf will not go to spit them and somehow split by zero when rendering colors.htm, if this grouping would be created there.
        List<Color> colors = service.getAllInOrderByValue();

        int size = colors.size();
        int groupSize = Math.max(1, size/4);

        List<List<Color>> groups = new ArrayList<>();

        for (int i = 0; i < 4; i++){
            int start = i *groupSize;
            int end = Math.min(start + groupSize, size);
            groups.add(colors.subList(start, end));
        }

        model.addAttribute("colors", colors);
        model.addAttribute("groups", groups);
        return "colors";
    }

    @GetMapping("/random-four")
    public String showRandomFourPage(Model model){
        model.addAttribute("picks", service.getRandomFourByValueGroups());
        return "random-four";
    }
}
