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
        List<Color> ordered = repository.getAllInOrderByValue();
        List<Color> filtered = filterExcludedColors(ordered);
        List<List<Color>> groups = splitIntoFourGroups(filtered);
        return pickRandomColorFromEachGroup(groups);
    }

    public List<List<Color>> groupColorsIntoFour() {
        List<Color> ordered = repository.getAllInOrderByValue();
        return splitIntoFourGroups(ordered);
    }

    private List<Color> filterExcludedColors(List<Color> colors){
        List<String> excluded = Arrays.asList(
                "white", "payne's grey", "mars black", "black"
        );

        List<Color> result = new ArrayList<>();
        for (Color c : colors){
            if (!excluded.contains(c.getName().toLowerCase())){
                result.add(c);
            }
        }
        return result;
    }

    private List<List<Color>> splitIntoFourGroups(List<Color> colors){
        int size = colors.size();
        int groupSize = Math.max(1, size/4);

        List<List<Color>> groups = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            int start = i * groupSize;
            int end = Math.min(start + groupSize, size);
            groups.add(colors.subList(start, end));
        }
        return groups;
    }

    private List<Color> pickRandomColorFromEachGroup(List<List<Color>> groups){
        Random random = new Random();
        List<Color> picks = new ArrayList<>();

        for (List<Color> group : groups){
            picks.add(group.get(random.nextInt(group.size())));
        }
        return picks;
    }

}
