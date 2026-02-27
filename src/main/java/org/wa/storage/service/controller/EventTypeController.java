package org.wa.storage.service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wa.storage.service.dto.EventTypeInfoDto;
import org.wa.storage.service.service.EventTypeService;

import java.util.List;

@RestController
@RequestMapping("/v1/event-types")
@RequiredArgsConstructor
public class EventTypeController {

    private final EventTypeService eventTypeService;

    @GetMapping
    public List<EventTypeInfoDto> getEventTypes() {
        return eventTypeService.getAllEventTypes();
    }
}
