package com.vapasi.hotelmenu.service;

import com.vapasi.hotelmenu.dto.MenuDto;
import com.vapasi.hotelmenu.model.Menu;
import com.vapasi.hotelmenu.repo.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;
    public MenuService(MenuRepository menuRepository)   {
        this.menuRepository = menuRepository;
    }
    public MenuDto save(MenuDto menuRequest) {
        Menu savedMenu = menuRepository.save(new Menu(menuRequest.getMenuItem(), menuRequest.getPrice()));
        return new MenuDto(savedMenu.getId(), savedMenu.getMenuItem(), savedMenu.getPrice());
    }

    public List<MenuDto> findMenu() {
        List<Menu> menuList = menuRepository.findAll();
        List<MenuDto> menuResponse = new ArrayList<>();
        for (Menu menu : menuList)  {
            MenuDto menuDto = new MenuDto(menu.getId(), menu.getMenuItem(), menu.getPrice());
            menuResponse.add(menuDto);
        }
        return menuResponse;
    }

    public MenuDto updateMenu(MenuDto menu) {
        Menu menuItem = menuRepository.findByMenuItem(menu.getMenuItem());
        menuItem.setPrice(menu.getPrice());
        Menu savedMenuItem = menuRepository.save(menuItem);
        return new MenuDto(savedMenuItem.getId(), savedMenuItem.getMenuItem(), savedMenuItem.getPrice());
    }

    public void deleteMenu(String menuItem) {
       menuRepository.deleteByMenuItem(menuItem);
    }
}
