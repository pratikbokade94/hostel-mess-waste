package com.messwaste.controller;

import com.messwaste.dto.FeedbackRequest;
import com.messwaste.entity.Feedback;
import com.messwaste.repository.FeedbackRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackRepository feedbackRepository;

    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    // Student submits feedback for a meal
    @PostMapping("/submit")
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackRequest request) {
        Feedback feedback = new Feedback();
        feedback.setUserId(request.getUserId());
        feedback.setMealDate(request.getMealDate());
        feedback.setMealType(request.getMealType());
        feedback.setRating(request.getRating());
        feedback.setComment(request.getComment());

        feedbackRepository.save(feedback);
        return ResponseEntity.ok(feedback);
    }

    // View all feedback for a specific date, with average rating
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getFeedbackByDate(@PathVariable String date) {
        LocalDate mealDate = LocalDate.parse(date);
        List<Feedback> feedbackList = feedbackRepository.findByMealDate(mealDate);

        double avgRating = feedbackList.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        return ResponseEntity.ok(Map.of(
                "mealDate", mealDate,
                "totalFeedback", feedbackList.size(),
                "averageRating", avgRating,
                "feedback", feedbackList
        ));
    }
}