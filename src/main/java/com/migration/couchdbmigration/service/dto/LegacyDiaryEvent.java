package com.migration.couchdbmigration.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LegacyDiaryEvent {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("_rev")
    private String rev;
    private String option;

}
