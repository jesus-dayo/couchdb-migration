package com.migration.couchdbmigration.integration.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.migration.couchdbmigration.config.CouchConfig;
import com.migration.couchdbmigration.integration.dto.AllDocsDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class CouchDBService<I, O> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final CouchConfig couchConfig;
    private final RestTemplate restTemplate;

    public AllDocsDTO getAll(){
        URI endPointUri = UriComponentsBuilder.newInstance()
                .scheme(couchConfig.getScheme())
                .host(couchConfig.getHost())
                .port(couchConfig.getPort())
                .path(couchConfig.getDatabase())
                .path(couchConfig.getApi().getGetAllDocs())
                .buildAndExpand().toUri();
        ResponseEntity<AllDocsDTO> allDocs = restTemplate.getForEntity(endPointUri, AllDocsDTO.class);
        return allDocs.getBody();
    }
    
    public I getDoc(String key, Class<I> docType){
        URI endPointUri = UriComponentsBuilder.newInstance()
                .scheme(couchConfig.getScheme())
                .host(couchConfig.getHost())
                .port(couchConfig.getPort())
                .path(couchConfig.getDatabase())
                .path("/")
                .path(key)
                .buildAndExpand().toUri();
        ResponseEntity<I> doc = restTemplate.getForEntity(endPointUri, docType);
        return doc.getBody();
    }

    public void updateDoc(String key, O doc){
        URI endPointUri = UriComponentsBuilder.newInstance()
                .scheme(couchConfig.getScheme())
                .host(couchConfig.getHost())
                .port(couchConfig.getPort())
                .path(couchConfig.getDatabase())
                .path("/")
                .path(key)
                .buildAndExpand().toUri();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            restTemplate.put(endPointUri, objectMapper.writeValueAsString(doc));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

}
