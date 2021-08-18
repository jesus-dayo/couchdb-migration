package com.migration.couchdbmigration.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.migration.couchdbmigration.service.DiaryMigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MigrationController {

    private final DiaryMigrationService diaryMigrationService;

    @PostMapping("/migrate")
    public ResponseEntity<Integer> migrate() throws JsonProcessingException {
        return ResponseEntity.ok(diaryMigrationService.migrate());
    }

}
