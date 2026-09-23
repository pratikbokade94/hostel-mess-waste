package com.messwaste.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    private String id;

    private Long userId;

    private LocalDate mealDate;

    private MenuItem.MealType mealType;

    private int rating; // 1 to 5

    private String comment;

    private LocalDateTime createdAt = LocalDateTime.now();
}