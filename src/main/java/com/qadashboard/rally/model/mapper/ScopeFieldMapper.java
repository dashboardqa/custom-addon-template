package com.qadashboard.rally.model.mapper;

import com.qadashboard.rally.domain.entity.ScopeField;
import com.qadashboard.rally.model.KeyLabel;
import com.qadashboard.rally.model.ScopeFieldResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ScopeFieldMapper {

    private static final String DELIMITER = "#";

    public ScopeFieldResponse toResponse(ScopeField scopeField) {
        final List<String> validators = Arrays.asList(scopeField.getValidators().split(DELIMITER));
        final List<String> affects = (scopeField.getAffects() != null) ?
                Arrays.asList(scopeField.getAffects().split(DELIMITER)) :
                Collections.emptyList();

        List<KeyLabel> content = getContent(scopeField.getContent());
        Map<String, Object> contentMap = new HashMap<>();
        contentMap.put("content", content);

        return ScopeFieldResponse.builder()
                .name(scopeField.getName())
                .key(scopeField.getKey())
                .type(scopeField.getType())
                .validators(validators)
                .placeholder(scopeField.getPlaceholder())
                .multiSelect(scopeField.isMultiSelect())
                .affects(affects)
                .data(contentMap)
                .build();
    }

    private List<KeyLabel> getContent(String content) {
        if (content == null) return null;

        return Arrays.stream(content.split(DELIMITER))
                .map(s -> KeyLabel.builder()
                        .key(s.toLowerCase())
                        .label(s).build())
                .collect(Collectors.toList());
    }
}