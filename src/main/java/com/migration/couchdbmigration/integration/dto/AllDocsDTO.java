package com.migration.couchdbmigration.integration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllDocsDTO {

    @JsonProperty("total_rows")
    private Integer totalRows;
    private Integer offset;
    private List<RowDTO> rows;

}
