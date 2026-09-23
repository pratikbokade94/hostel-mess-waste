package com.messwaste.dto;

import com.messwaste.entity.MenuItem;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MenuItemRequest {
    private LocalDate mealDate;
    private MenuItem.MealType mealType;
    private String dishName;
}