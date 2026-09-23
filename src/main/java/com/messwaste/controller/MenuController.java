package com.messwaste.controller;

import com.messwaste.dto.MenuItemRequest;
import com.messwaste.dto.OptOutRequest;
import com.messwaste.entity.MealOptOut;
import com.messwaste.entity.MenuItem;
import com.messwaste.repository.MealOptOutRepository;
import com.messwaste.repository.MenuItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuItemRepository menuItemRepository;
    private final MealOptOutRepository mealOptOutRepository;

    public MenuController(MenuItemRepository menuItemRepository, MealOptOutRepository mealOptOutRepository) {
        this.menuItemRepository = menuItemRepository;
        this.mealOptOutRepository = mealOptOutRepository;
    }

    // Staff/Admin adds a menu item for a date
    @PostMapping("/add")
    public ResponseEntity<?> addMenuItem(@RequestBody MenuItemRequest request) {
        MenuItem item = new MenuItem();
        item.setMealDate(request.getMealDate());
        item.setMealType(request.getMealType());
        item.setDishName(request.getDishName());
        menuItemRepository.save(item);
        return ResponseEntity.ok(item);
    }

    // View menu for a specific date
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getMenuByDate(@PathVariable String date) {
        LocalDate mealDate = LocalDate.parse(date);
        List<MenuItem> items = menuItemRepository.findByMealDate(mealDate);
        return ResponseEntity.ok(items);
    }

    // Student opts out of a meal
    @PostMapping("/optout")
    public ResponseEntity<?> optOut(@RequestBody OptOutRequest request) {
        if (mealOptOutRepository.findByUserIdAndMealDateAndMealType(
                request.getUserId(), request.getMealDate(), request.getMealType()).isPresent()) {
            return ResponseEntity.badRequest().body("Already opted out for this meal");
        }

        MealOptOut optOut = new MealOptOut();
        optOut.setUserId(request.getUserId());
        optOut.setMealDate(request.getMealDate());
        optOut.setMealType(request.getMealType());
        mealOptOutRepository.save(optOut);

        return ResponseEntity.ok("Opted out successfully");
    }

    // Staff sees how many students opted out for a meal (to estimate cooking quantity)
    @GetMapping("/optout-count")
    public ResponseEntity<?> getOptOutCount(@RequestParam String date, @RequestParam MenuItem.MealType mealType) {
        LocalDate mealDate = LocalDate.parse(date);
        long count = mealOptOutRepository.countByMealDateAndMealType(mealDate, mealType);
        return ResponseEntity.ok(Map.of("mealDate", mealDate, "mealType", mealType, "optOutCount", count));
    }
}