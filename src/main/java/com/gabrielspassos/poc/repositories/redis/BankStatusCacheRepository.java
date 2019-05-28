package com.gabrielspassos.poc.repositories.redis;

import com.gabrielspassos.poc.entities.redis.BankStatusCacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankStatusCacheRepository extends CrudRepository<BankStatusCacheEntity, String> {
}
