package com.messwaste.repository;

import com.messwaste.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByMealDate(LocalDate mealDate);
    List<MenuItem> findByMealDateBetween(LocalDate start, LocalDate end);
}