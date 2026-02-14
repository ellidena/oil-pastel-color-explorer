package com.example.colorpicker.controller;

import com.example.colorpicker.model.Color;
import com.example.colorpicker.service.ColorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ColorPageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ColorService service;

    @Test
    void showHomePage_ReturnsHomeView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    void showColorsPage_AddsGroupsToModel() throws Exception {
        List<List<Color>> fakeGroups = List.of(
                List.of(new Color("red", 1, "#ff0000"))
        );

        when(service.groupColorsIntoFour()).thenReturn(fakeGroups);

        mockMvc.perform(get("/colors"))
                .andExpect(status().isOk())
                .andExpect(view().name("colors"))
                .andExpect(model().attribute("groups", fakeGroups));

        verify(service).groupColorsIntoFour();
    }

    @Test
    void showRandomFourPage_AddsPicksToModel() throws Exception {
        List<Color> fakePicks = List.of(
                new Color("blue", 10, "#0000ff")
        );

        when(service.getRandomFourByValueGroups()).thenReturn(fakePicks);

        mockMvc.perform(get("/random-four"))
                .andExpect(status().isOk())
                .andExpect(view().name("random-four"))
                .andExpect(model().attribute("picks", fakePicks));

        verify(service).getRandomFourByValueGroups();
    }

    @Test
    void showColor_AddsColorToModel() throws Exception {
        Color fake = new Color("green", 5, "#00ff00");

        when(service.getByNumber(5)).thenReturn(fake);

        mockMvc.perform(get("/color/5"))
                .andExpect(status().isOk())
                .andExpect(view().name("color-detail"))
                .andExpect(model().attribute("color", fake));

        verify(service).getByNumber(5);
    }
}
