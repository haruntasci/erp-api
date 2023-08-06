package com.example.erp.service;

import com.example.erp.model.KeyValue;
import com.example.erp.repository.KeyValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeyValueService {

    private final KeyValueRepository keyValueRepository;

    public KeyValueService(KeyValueRepository keyValueRepository) {
        this.keyValueRepository = keyValueRepository;
    }

    public KeyValue saveKeyValue(String key, double value) {
        KeyValue keyValue = new KeyValue();
        keyValue.setKdvName(key);
        keyValue.setKdvValue(value);
        return keyValueRepository.save(keyValue);
    }

    public KeyValue getValueByKey(String key) {
        Optional<KeyValue> keyValue = keyValueRepository.findById(key);
        return keyValue.get();
    }

    public List<KeyValue> getAllKeyValues() {
        return keyValueRepository.findAll();
    }
}
