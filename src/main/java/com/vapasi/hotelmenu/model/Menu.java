package com.vapasi.hotelmenu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Menu {

    @Id
    @GeneratedValue
    private int id;
    private String menuItem;

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;

    public Menu()   {

    }

    public Menu(int id, String menuItem, int price) {
        this.id = id;
        this.menuItem = menuItem;
        this.price = price;
    }

    public Menu(String menuItem, int price) {
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
        Menu menu = (Menu) o;
        return  Double.compare(menu.price, price) == 0 &&
                Objects.equals(menuItem, menu.menuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuItem, price);
    }
}

