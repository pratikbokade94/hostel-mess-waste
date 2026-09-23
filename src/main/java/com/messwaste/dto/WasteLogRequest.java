package com.messwaste.dto;

import com.messwaste.entity.MenuItem;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WasteLogRequest {
    private LocalDate mealDate;
    private MenuItem.MealType mealType;
    private BigDecimal preparedKg;
    private BigDecimal consumedKg;
    private String notes;
}