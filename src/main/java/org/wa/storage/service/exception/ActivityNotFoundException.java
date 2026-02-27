package org.wa.storage.service.exception;

import org.apache.kafka.common.errors.ResourceNotFoundException;

public class ActivityNotFoundException extends ResourceNotFoundException {
    public ActivityNotFoundException(String message) {
        super(message);
    }
}
