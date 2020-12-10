package com.vapasi.hotelmenu.repo;

import com.vapasi.hotelmenu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    public Menu findByMenuItem(String menuItem);

    @Transactional
    public void deleteByMenuItem(String menuItem);
}
