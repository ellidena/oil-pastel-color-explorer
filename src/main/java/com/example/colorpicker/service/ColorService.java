package com.example.colorpicker.service;

import com.example.colorpicker.model.Color;
import com.example.colorpicker.repository.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ColorService {

    private final ColorRepository repository;

    public ColorService(ColorRepository repository){
        this.repository = repository;
    }

    public Collection<Color> getAllColors(){
        return repository.getAll();
    }

    public List<Color> getAllInOrderByValue(){
        return repository.getAllInOrderByValue();
    }

    public Color getByName(String name){
        return repository.getByName(name);
    }

    public Color getByNumber(int number){
        return repository.getByNumber(number);
    }

    public List<Color> getRandomFourByValueGroups(){
        // 1. Get ordered list
        List<Color> ordered = repository.getAllInOrderByValue();

        // 2. Exclude specific colors
        List<String> excluded = Arrays.asList(
                "white",
                "payne's grey",
                "mars black",
                "black"
        );

        List<Color> filtered = new ArrayList<>();
        for (Color color : ordered){
            String name = color.getName().toLowerCase();
            if (!excluded.contains(name)){
                filtered.add(color);
            }
        }

        // 3. split into four groups
        int size = filtered.size();
        int groupSize = size/4;

        List<Color> picks = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 4; i++){
            int start = i * groupSize;
            int end = (i == 3) ? size : start + groupSize;

            List<Color> group = filtered.subList(start, end);

            // 4. pick a random color from this group
            Color randomPick = group.get(random.nextInt(group.size()));
            picks.add(randomPick);
        }
        return picks;
    }
}
