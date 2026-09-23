package com.messwaste.repository;

import com.messwaste.entity.MealOptOut;
import com.messwaste.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MealOptOutRepository extends JpaRepository<MealOptOut, Long> {
    List<MealOptOut> findByMealDateAndMealType(LocalDate mealDate, MenuItem.MealType mealType);
    Optional<MealOptOut> findByUserIdAndMealDateAndMealType(Long userId, LocalDate mealDate, MenuItem.MealType mealType);
    long countByMealDateAndMealType(LocalDate mealDate, MenuItem.MealType mealType);
}