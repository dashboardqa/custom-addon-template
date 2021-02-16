package com.qadashboard.rally.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Getter
@Setter
@Entity
public class ScopeField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String key;
    private String type;
    private String validators;
    private String placeholder;
    private boolean multiSelect;
    private String affects;
    private String content;
    @ManyToOne
    private Scope scope;
}