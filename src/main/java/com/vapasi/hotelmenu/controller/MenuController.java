package com.vapasi.hotelmenu.controller;

import com.vapasi.hotelmenu.dto.MenuDto;
import com.vapasi.hotelmenu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }


    @PostMapping
    public ResponseEntity<MenuDto> saveMenu(@RequestBody MenuDto menu) throws URISyntaxException {

        return ResponseEntity.created(new URI("/menu")).body(menuService.save(menu));
    }

    @GetMapping("/find")
    public ResponseEntity<List<MenuDto>> findAll()  {
        return ResponseEntity.ok(menuService.findMenu());
    }

    @PutMapping("/update")
    public ResponseEntity<MenuDto> update(@RequestBody MenuDto menu)    {
        return ResponseEntity.ok(menuService.updateMenu(menu));
    }

    @DeleteMapping("/delete/{item}")
    public ResponseEntity deleteMenu(@PathVariable String item)   {
        menuService.deleteMenu(item.trim());
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

}
