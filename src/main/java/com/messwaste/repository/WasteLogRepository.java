package com.messwaste.repository;

import com.messwaste.entity.WasteLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WasteLogRepository extends JpaRepository<WasteLog, Long> {
    List<WasteLog> findByMealDate(LocalDate mealDate);
    List<WasteLog> findByMealDateBetween(LocalDate start, LocalDate end);
}