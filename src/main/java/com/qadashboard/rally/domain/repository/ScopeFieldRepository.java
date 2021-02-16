package com.qadashboard.rally.domain.repository;

import com.qadashboard.rally.domain.entity.ScopeField;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScopeFieldRepository extends CrudRepository<ScopeField, Long> {

    Optional<List<ScopeField>> findAllByScope_Id(Long scopeId);
}
