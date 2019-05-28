package com.gabrielspassos.poc.controllers;

import com.gabrielspassos.poc.controllers.response.BankStatusResponse;
import com.gabrielspassos.poc.dto.BankStatusDTO;
import com.gabrielspassos.poc.enumerators.BankStatusEnum;
import com.gabrielspassos.poc.services.BankService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Stream;

@RestController
public class BankStatusController {

    private final BankService bankService;
    private final ModelMapper modelMapper;

    private static final int OK = 200;
    private static final String OK_MESSAGE = "Successful operation.";

    @Autowired
    public BankStatusController(BankService bankService, ModelMapper modelMapper) {
        this.bankService = bankService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/v1/bank/status")
    @ApiResponses(value = {
            @ApiResponse(code = OK, message = OK_MESSAGE, response = BankStatusResponse.class)
    })
    public ResponseEntity<?> getBankStatus() {
        return Stream.of(bankService.getBankStatus())
                .map(this::convertDTOToResponse)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    @PatchMapping(value = "/v1/bank/status")
    public ResponseEntity<?> updateBankStatus(@RequestParam @Valid BankStatusEnum newBankStatus) {
        return Stream.of(bankService.updateBankStatus(newBankStatus.name()))
                .map(this::convertDTOToResponse)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    private BankStatusResponse convertDTOToResponse(BankStatusDTO bankStatusDTO) {
        return modelMapper.map(bankStatusDTO, BankStatusResponse.class);
    }
}
