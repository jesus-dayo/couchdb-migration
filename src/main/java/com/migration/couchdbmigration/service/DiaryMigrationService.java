package com.migration.couchdbmigration.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migration.couchdbmigration.integration.dto.AllDocsDTO;
import com.migration.couchdbmigration.integration.dto.RowDTO;
import com.migration.couchdbmigration.integration.services.CouchDBService;
import com.migration.couchdbmigration.service.dto.DiaryEvent;
import com.migration.couchdbmigration.service.dto.LegacyDiaryEvent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class DiaryMigrationService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final CouchDBService<LegacyDiaryEvent, DiaryEvent> couchDBService;

    public Integer migrate() {
        AllDocsDTO all = couchDBService.getAll();
        if(Objects.isNull(all) || all.getTotalRows() == 0){
            return 0;
        }
        log.info("All docs {}",all);
        all.getRows().forEach(row->{
            DiaryEvent newDiaryEvent = getNewDiaryEvent(row);
            couchDBService.updateDoc(row.getKey(), newDiaryEvent);
        });
        return 0;
    }

    private DiaryEvent getNewDiaryEvent(RowDTO row) {
        LegacyDiaryEvent doc = couchDBService.getDoc(row.getKey(), LegacyDiaryEvent.class);
        if(Objects.isNull(doc)) {
            return null;
        }
        return DiaryEvent.builder()
                .id(doc.getId())
                .rev(doc.getRev())
                .option(new String[]{doc.getOption()}).build();
    }

}
