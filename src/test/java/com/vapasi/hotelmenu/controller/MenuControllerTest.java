package com.vapasi.hotelmenu.controller;

import com.vapasi.hotelmenu.dto.MenuDto;
import com.vapasi.hotelmenu.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class MenuControllerTest {
    @MockBean
    private MenuService menuService;
    @Autowired
    private MockMvc mockMVC;

    @Test
    public void shouldSaveMenu() throws Exception {
        when(menuService.save(new MenuDto("Noodles", 150))).thenReturn(new MenuDto(1,"Noodles", 150));

        // assert on data
        mockMVC.perform(post("/menu")
                .content("{\"menuItem\": \"Noodles\"," +
                        "\"price\": \"150\"}")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
        verify(menuService).save(new MenuDto("Noodles", 150));
    }

    @Test
    public void shouldReturnMenu() throws Exception {
        List<MenuDto>menuServiceResponse = new ArrayList<>();
        menuServiceResponse.add(new MenuDto(1,"Idli", 60));
        menuServiceResponse.add(new MenuDto(2,"Dosa", 100));
        menuServiceResponse.add(new MenuDto(3,"Vada", 40));
        when(menuService.findMenu()).thenReturn(menuServiceResponse);
        mockMVC.perform(get("/menu/find").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(menuService).findMenu();
    }

    @Test
    public void shouldUpdateMenu() throws Exception {
        MenuDto requestMenu = new MenuDto("Idli", 90);
        when(menuService.updateMenu(requestMenu)).thenReturn(new MenuDto(1, "Idli", 90));
        mockMVC.perform(put("/menu/update")
                .content("{\"menuItem\": \"Idli\"," +
                        "\"price\": \"90\"}")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(menuService).updateMenu(requestMenu);
    }

    @Test
    public void shouldDeleteMenuItem() throws Exception {
        doNothing().when(menuService).deleteMenu("Idli");
        mockMVC.perform(delete("/menu/delete/Idli")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(menuService).deleteMenu("Idli");
    }
}
