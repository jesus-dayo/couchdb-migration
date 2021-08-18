package com.migration.couchdbmigration.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class DiaryEvent {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("_rev")
    private String rev;
    @JsonProperty("option")
    private String[] option;

}
