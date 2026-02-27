package org.wa.storage.service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wa.storage.service.dto.EventTypeInfoDto;
import org.wa.storage.service.enums.EventType;
import org.wa.storage.service.mapper.EventTypeMapper;
import org.wa.storage.service.service.EventTypeService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventTypeServiceImpl implements EventTypeService {

    private final EventTypeMapper eventTypeMapper;

    public List<EventTypeInfoDto> getAllEventTypes() {
        return Arrays.stream(EventType.values())
                .map(eventTypeMapper::toDto)
                .collect(Collectors.toList());
    }
}
