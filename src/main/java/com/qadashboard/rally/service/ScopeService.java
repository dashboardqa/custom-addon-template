package com.qadashboard.rally.service;

import com.qadashboard.rally.domain.entity.Scope;
import com.qadashboard.rally.domain.repository.ScopeRepository;
import com.qadashboard.rally.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScopeService {

    private final ScopeRepository scopeRepository;

    public List<Scope> findAll() {
        return scopeRepository.findAll();
    }

    public Scope findById(Long id) {
        return scopeRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }
}
