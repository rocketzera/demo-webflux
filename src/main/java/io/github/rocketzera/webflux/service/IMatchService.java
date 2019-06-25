package io.github.rocketzera.webflux.service;

import io.github.rocketzera.webflux.domain.MatchDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMatchService {
    Mono<MatchDTO> save(MatchDTO dto);
    Mono<MatchDTO> enterInExistingMatch(String code);
    Flux<MatchDTO> findAll();
}
