package com.messwaste.controller;

import com.messwaste.dto.WasteLogRequest;
import com.messwaste.entity.WasteLog;
import com.messwaste.repository.WasteLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/waste")
public class WasteController {

    private final WasteLogRepository wasteLogRepository;

    public WasteController(WasteLogRepository wasteLogRepository) {
        this.wasteLogRepository = wasteLogRepository;
    }

    // Staff logs waste after a meal
    @PostMapping("/log")
    public ResponseEntity<?> logWaste(@RequestBody WasteLogRequest request) {
        BigDecimal wasted = request.getPreparedKg().subtract(request.getConsumedKg());

        WasteLog log = new WasteLog();
        log.setMealDate(request.getMealDate());
        log.setMealType(request.getMealType());
        log.setPreparedKg(request.getPreparedKg());
        log.setConsumedKg(request.getConsumedKg());
        log.setWastedKg(wasted);
        log.setNotes(request.getNotes());

        wasteLogRepository.save(log);
        return ResponseEntity.ok(log);
    }

    // View all waste logs for a specific date
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getWasteByDate(@PathVariable String date) {
        LocalDate mealDate = LocalDate.parse(date);
        List<WasteLog> logs = wasteLogRepository.findByMealDate(mealDate);
        return ResponseEntity.ok(logs);
    }

    // Report: total waste and cost lost between two dates
    @GetMapping("/report")
    public ResponseEntity<?> getReport(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<WasteLog> logs = wasteLogRepository.findByMealDateBetween(start, end);

        BigDecimal totalPrepared = BigDecimal.ZERO;
        BigDecimal totalConsumed = BigDecimal.ZERO;
        BigDecimal totalWasted = BigDecimal.ZERO;
        BigDecimal totalCostLost = BigDecimal.ZERO;

        for (WasteLog log : logs) {
            totalPrepared = totalPrepared.add(log.getPreparedKg());
            totalConsumed = totalConsumed.add(log.getConsumedKg());
            totalWasted = totalWasted.add(log.getWastedKg());
            totalCostLost = totalCostLost.add(log.getWastedKg().multiply(log.getCostPerKg()));
        }

        return ResponseEntity.ok(Map.of(
                "startDate", start,
                "endDate", end,
                "totalEntries", logs.size(),
                "totalPreparedKg", totalPrepared,
                "totalConsumedKg", totalConsumed,
                "totalWastedKg", totalWasted,
                "estimatedCostLost", totalCostLost
        ));
    }
}