package com.messwaste.repository;

import com.messwaste.entity.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findByMealDate(java.time.LocalDate mealDate);
}