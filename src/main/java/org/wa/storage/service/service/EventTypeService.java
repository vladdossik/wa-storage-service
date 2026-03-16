package org.wa.storage.service.service;

import org.wa.storage.service.dto.EventTypeInfoDto;

import java.util.List;

public interface EventTypeService {
    List<EventTypeInfoDto> getAllEventTypes();
}
