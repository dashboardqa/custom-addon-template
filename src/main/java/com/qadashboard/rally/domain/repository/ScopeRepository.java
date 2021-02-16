package com.qadashboard.rally.domain.repository;

import com.qadashboard.rally.domain.entity.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Long> {
}
