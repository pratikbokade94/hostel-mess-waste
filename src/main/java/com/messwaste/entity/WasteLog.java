package com.messwaste.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "waste_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate mealDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuItem.MealType mealType;

    @Column(nullable = false)
    private BigDecimal preparedKg;

    @Column(nullable = false)
    private BigDecimal consumedKg;

    @Column(nullable = false)
    private BigDecimal wastedKg;

    @Column(nullable = false)
    private BigDecimal costPerKg = new BigDecimal("60.00");

    private String notes;

    private LocalDateTime createdAt = LocalDateTime.now();
}