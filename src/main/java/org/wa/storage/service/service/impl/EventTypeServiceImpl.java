package org.wa.storage.service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wa.storage.service.dto.EventTypeInfoDto;
import org.wa.storage.service.enumeration.EventType;
import org.wa.storage.service.service.EventTypeService;

import java.util.List;

@Slf4j
@Service
public class EventTypeServiceImpl implements EventTypeService {

    @Override
    public List<EventTypeInfoDto> getAllEventTypes() {
        return EventType.getAllAsInfo();
    }
}
