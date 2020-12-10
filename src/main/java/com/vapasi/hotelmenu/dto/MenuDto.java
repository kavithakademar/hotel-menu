package com.vapasi.hotelmenu.dto;

import java.util.Objects;

public class MenuDto {
    private int id;
    private String menuItem;
    private int price;

    public MenuDto(){}
    public MenuDto(String menuItem, int price) {
        this.menuItem = menuItem;
        this.price = price;
    }
    public MenuDto(int id, String menuItem, int price) {
        this.id = id;
        this.menuItem = menuItem;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuDto menuDto = (MenuDto) o;
        return id == menuDto.id &&
                price == menuDto.price &&
                Objects.equals(menuItem, menuDto.menuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuItem, price);
    }
}
