package com.vapasi.hotelmenu.repo;

import com.vapasi.hotelmenu.model.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void shouldSaveMenu()    {
        Menu menu = new Menu("Dosa", 50);
        Menu savedMenu = menuRepository.save(menu);
        Menu expectedMenu = new Menu(1,"Dosa",50);
        assertEquals(expectedMenu, savedMenu);
    }
    @Test
    public void shouldReturnAllMenuItems()  {
        Menu item1 = new Menu("Idli",60);
        Menu item2 = new Menu("Dosa", 100);
        Menu item3 = new Menu("Vada", 40);
        menuRepository.save(item1);
        menuRepository.save(item2);
        menuRepository.save(item3);
        List<Menu> menu = new ArrayList<>();
        menu.add(new Menu(1,"Idli", 60));
        menu.add(new Menu(2,"Dosa", 100));
        menu.add(new Menu(3,"Vada", 40));
        List<Menu> actualOutput = menuRepository.findAll();
            assertEquals(menu, actualOutput);
    }

    @Test
    public void shouldUpdateMenu()  {
        Menu item1 = new Menu("Idli",60);
        Menu item2 = new Menu("Dosa", 100);
        Menu item3 = new Menu("Vada", 40);
        menuRepository.save(item1);
        menuRepository.save(item2);
        menuRepository.save(item3);
        Menu item = menuRepository.findByMenuItem("Dosa");
        item.setPrice(200);
        Menu savedItem = menuRepository.save(item);
        assertEquals(item, savedItem);
    }

    @Test
    public void shouldDeleteRecord()    {
        Menu item1 = new Menu("Idli",60);
        Menu item2 = new Menu("Dosa", 100);
        Menu item3 = new Menu("Vada", 40);
        menuRepository.save(item1);
        menuRepository.save(item2);
        menuRepository.save(item3);
        menuRepository.deleteByMenuItem("Dosa");
        List<Menu> actualOutput = menuRepository.findAll();
        List<Menu> expectedOuput = new ArrayList<>();
        expectedOuput.add(new Menu(1,"Idli",60));
        expectedOuput.add(new Menu(3,"Vada", 40));
        assertEquals(expectedOuput, actualOutput);
    }
}
