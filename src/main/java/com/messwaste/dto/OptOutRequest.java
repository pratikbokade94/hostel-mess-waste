package com.messwaste.dto;

import com.messwaste.entity.MenuItem;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OptOutRequest {
    private Long userId;
    private LocalDate mealDate;
    private MenuItem.MealType mealType;
}