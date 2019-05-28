package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.dto.BankStatusDTO;
import com.gabrielspassos.poc.entities.mysql.BankStatusEntity;
import com.gabrielspassos.poc.entities.redis.BankStatusCacheEntity;
import com.gabrielspassos.poc.enumerators.BankStatusEnum;
import com.gabrielspassos.poc.repositories.mysql.BankStatusRepository;
import com.gabrielspassos.poc.repositories.redis.BankStatusCacheRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class BankService {

    private final BankStatusRepository bankStatusRepository;
    private final BankStatusCacheRepository bankStatusCacheRepository;

    @Autowired
    public BankService(BankStatusRepository bankStatusRepository, BankStatusCacheRepository bankStatusCacheRepository) {
        this.bankStatusRepository = bankStatusRepository;
        this.bankStatusCacheRepository = bankStatusCacheRepository;
    }

    public BankStatusDTO getBankStatus() {
        List<BankStatusCacheEntity> bankStatusCacheEntities = Lists.newArrayList(bankStatusCacheRepository.findAll());
        return bankStatusCacheEntities.stream()
                .findFirst()
                .map(bankStatusCacheEntity -> buildBankStatusDTO(
                        bankStatusCacheEntity.getId(), bankStatusCacheEntity.getStatus())
                ).get();
    }

    public BankStatusDTO updateBankStatus(String status) {
        return Stream.of(bankStatusRepository.findTopByOrderByIdDesc())
                .peek(bankStatusEntity -> bankStatusEntity.setStatus(status))
                .map(bankStatusRepository::save)
                .map(updatedBankStatus -> buildBankStatusDTO(String.valueOf(updatedBankStatus.getId()), updatedBankStatus.getStatus()))
                .findFirst()
                .get();
    }

    public BankStatusCacheEntity updateCachedBankStatus() {
        BankStatusEntity bankStatusEntity = bankStatusRepository.findTopByOrderByIdDesc();
        List<BankStatusCacheEntity> bankStatusCacheEntities = Lists.newArrayList(bankStatusCacheRepository.findAll());
        return bankStatusCacheEntities.stream()
                .findFirst()
                .map(bankStatusCacheEntity -> {
                    bankStatusCacheEntity.setStatus(bankStatusEntity.getStatus());
                    return bankStatusCacheEntity;
                }).map(bankStatusCacheRepository::save)
                .get();
    }

    private BankStatusDTO buildBankStatusDTO(String id, String status) {
        BankStatusDTO bankStatusDTO = new BankStatusDTO();
        bankStatusDTO.setId(id);
        bankStatusDTO.setStatus(status);
        bankStatusDTO.setIsBankActive(isBankStatusActive(status));
        return bankStatusDTO;
    }

    private Boolean isBankStatusActive(String bankStatus) {
        return BankStatusEnum.ACTIVE.name().equals(bankStatus);
    }
}
