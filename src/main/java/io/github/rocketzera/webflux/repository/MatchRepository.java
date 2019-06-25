package io.github.rocketzera.webflux.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import io.github.rocketzera.webflux.domain.MatchEntity;

public interface MatchRepository extends ReactiveMongoRepository<MatchEntity, String> {
    
    Optional<MatchEntity> findByCode(String code);
    
}
