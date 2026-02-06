package com.example.colorpicker.repository;

import com.example.colorpicker.model.Color;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

@Repository
public class ColorRepository {
    /**
     * Map<Integer,Color> and Map<String,Color>
     * - Fast lookups by number or name
     * - Easy to update or replace
     * - prevents duplicate keys
     * - reads nicely here, I can use getByNumber(int i)
     * - the numbers are an ID-like field
     */
    private Map<Integer,Color> colorsByNumber = new HashMap<>();
    private Map<String, Color> colorsByName = new HashMap<>();
    private final List<Color> colorsInOrderByValue = new ArrayList<>();

    /**
     * @PostConstruct loads the CSV automatically when the app starts
     * the constructor isn't needed at all now actually.
     * Spring expects to create the bean with a no-arg constructor
     */
    @PostConstruct
    public void initialize(){
        loadFromCsv("/colors.csv");
    }

    // empty constructor for unit tests
    public ColorRepository(){}

    public void add(Color color){
        colorsByNumber.put(color.getNumber(),color);
        colorsByName.put(color.getName(),color);
        colorsInOrderByValue.add(color); // preserves CSV order
    }

    public Color getByNumber(int number){
        return colorsByNumber.get(number);
    }

    public Color getByName(String name){
        return colorsByName.get(name);
    }

    public Collection<Color> getAll(){
        return colorsByNumber.values();
    }

    public List<Color> getAllInOrderByValue(){
        return colorsInOrderByValue;
    }

    public void loadFromCsv(String resourcePath) {
        /*
        getResourceAsStream() returns null if the resource isn't found.
         */
        InputStream in = getClass().getResourceAsStream(resourcePath);
        if (in ==null){
            throw new IllegalArgumentException("Resource not found: "+ resourcePath);
        }
        Scanner scanner = new Scanner(in);

        boolean first = true;

        while (scanner.hasNextLine()){

            String line = scanner.nextLine();
            if (first){
                first = false; // skip header
                continue;
            }

            String[] parts = line.split(",");
            String name = parts[0].trim();
            int number = Integer.parseInt(parts[1].trim());
            String hex = parts[2].trim();
            add(new Color(name, number, hex));
        }
    }


}
