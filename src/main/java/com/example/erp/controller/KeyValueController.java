package com.example.erp.controller;

import com.example.erp.model.KeyValue;
import com.example.erp.service.KeyValueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/key-value")
public class KeyValueController {

    private final KeyValueService keyValueService;

    public KeyValueController(KeyValueService keyValueService) {
        this.keyValueService = keyValueService;
    }

    @PostMapping
    public ResponseEntity<KeyValue> createKeyValue(@RequestParam String key, @RequestParam Double value) {
        return new ResponseEntity<>(keyValueService.saveKeyValue(key, value), HttpStatus.OK);
    }

    @GetMapping("/{key}")
    public ResponseEntity<KeyValue> getValueByKey(@PathVariable String key) {
        return new ResponseEntity<>(keyValueService.getValueByKey(key), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<KeyValue>> getAllKeyValues() {
        return new ResponseEntity<>(keyValueService.getAllKeyValues(), HttpStatus.OK);
    }

}
