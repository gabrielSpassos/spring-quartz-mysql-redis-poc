package com.gabrielspassos.poc.controllers;

import com.gabrielspassos.poc.controllers.request.ScheduleTriggerRequest;
import com.gabrielspassos.poc.controllers.response.ScheduleTriggerResponse;
import com.gabrielspassos.poc.entities.mysql.CronTriggersEntity;
import com.gabrielspassos.poc.services.ScheduleService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.stream.Stream;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ModelMapper modelMapper;

    private static final int OK = 200;
    private static final String OK_MESSAGE = "Successful operation.";

    @Autowired
    public ScheduleController(ScheduleService scheduleService, ModelMapper modelMapper) {
        this.scheduleService = scheduleService;
        this.modelMapper = modelMapper;
    }

    @PatchMapping(value = "/v1/schedules/triggers/{triggerName}")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = ScheduleTriggerResponse.class)
    })
    public ResponseEntity<?> updateScheduleTrigger(@PathVariable String triggerName,
                                                   @RequestBody @Valid ScheduleTriggerRequest scheduleTriggerRequest) throws ParseException {
        CronTriggersEntity cronTriggersEntity = scheduleService.updateScheduleTriggerCronExpression(
                triggerName, scheduleTriggerRequest.getNewCronExpression()
        );
        return Stream.of(cronTriggersEntity)
                .map(this::convertEntityToResponse)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    private ScheduleTriggerResponse convertEntityToResponse(CronTriggersEntity cronTriggersEntity) {
        return modelMapper.map(cronTriggersEntity, ScheduleTriggerResponse.class);
    }
}
