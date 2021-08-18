package com.migration.couchdbmigration.integration.dto;

import lombok.Data;

@Data
public class RowDTO {

    private String id;
    private String key;
    private ValueDTO value;

}
