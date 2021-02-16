package com.qadashboard.rally.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String message) {
        super(message);
    }
}
