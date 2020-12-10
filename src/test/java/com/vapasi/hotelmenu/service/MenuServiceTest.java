package com.vapasi.hotelmenu.service;

import com.vapasi.hotelmenu.dto.MenuDto;
import com.vapasi.hotelmenu.model.Menu;
import com.vapasi.hotelmenu.repo.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceTest {

    @Mock
    MenuRepository menuRepository;
    @Autowired
    MenuService menuService;

    @Before
    public void setup() {
        menuService = new MenuService(menuRepository);
    }
    @Test
    public void shouldSaveMenu()    {
        MenuDto menuRequest = new MenuDto("Noodles", 100);
        Menu menu = new Menu("Noodles", 100);
        when(menuRepository.save(menu)).thenReturn(new Menu(1,"Noodles", 100));
        MenuDto menuResponse = menuService.save(menuRequest);
        assertEquals(new MenuDto(1, "Noodles", 100), menuResponse);
        verify(menuRepository).save(menu);
    }

    @Test
    public void shouldReturnAllMenus()  {
        List<Menu> menu = new ArrayList<>();
        menu.add(new Menu(1,"Idli", 60));
        menu.add(new Menu(2,"Dosa", 100));
        menu.add(new Menu(3,"Vada", 40));
        when(menuRepository.findAll()).thenReturn(menu);
        List<MenuDto> menuResponse = menuService.findMenu();
        List<MenuDto> expectedMenuResponse = new ArrayList<>();
        expectedMenuResponse.add(new MenuDto(1,"Idli", 60));
        expectedMenuResponse.add(new MenuDto(2,"Dosa", 100));
        expectedMenuResponse.add(new MenuDto(3,"Vada", 40));
        assertEquals(expectedMenuResponse, menuResponse);
        verify(menuRepository).findAll();

    }

    @Test
    public void shouldUpdateMenu()  {
        Menu menu = new Menu(1,"Idli", 60);
        when(menuRepository.findByMenuItem("Idli")).thenReturn(menu);
        Menu menuUpdated = new Menu(1,"Idli", 90);
        when(menuRepository.save(menuUpdated)).thenReturn(menuUpdated);
        MenuDto responseMenu = menuService.updateMenu(new MenuDto("Idli", 90));
        MenuDto expectedResponse = new MenuDto(1, "Idli", 90);
        assertEquals(expectedResponse, responseMenu);
        verify(menuRepository).findByMenuItem("Idli");
        verify(menuRepository).save(menuUpdated);
    }
    @Test
    public void shouldDeleteMenu()  {
        doNothing().when(menuRepository).deleteByMenuItem("Idli");
        menuService.deleteMenu("Idli");
        verify(menuRepository).deleteByMenuItem("Idli");
    }
}
