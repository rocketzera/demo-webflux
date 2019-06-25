package io.github.rocketzera.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.rocketzera.webflux.domain.MatchDTO;
import io.github.rocketzera.webflux.domain.MatchEntity;
import io.github.rocketzera.webflux.repository.MatchRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MatchService implements IMatchService {

    private static final int MAX_LIMIT_PLAYERS = 6;
    
    @Autowired
    private MatchRepository repository;
    
    @Override
    public Mono<MatchDTO> save(MatchDTO dto) {
        return repository.save(new MatchEntity(dto)).map(MatchDTO::new);
    }
    
    private Mono<MatchEntity> saveAndAddPlayer(MatchEntity entity) {
        System.out.println("Save And Add Player...");
        entity.setNumberOfPlayers(entity.getNumberOfPlayers() + 1);
        return repository.save(entity);
    }

    @Override
    public Mono<MatchDTO> enterInExistingMatch(String code) {
        Mono<MatchEntity> publisher = repository.findById(code)
                .filter(this::checkPreConditionalsToEnterInExistingMatch)
                .switchIfEmpty(Mono.<MatchEntity>error(new RuntimeException("Match is over or limit of players exceed!")))
                .flatMap(this::saveAndAddPlayer);
        return publisher.map(MatchDTO::new);
    }

    @Override
    public Flux<MatchDTO> findAll() {
        return repository.findAll().map(MatchDTO::new);
    }
    
    private boolean checkPlayersLimitByMatch(MatchEntity entity) {
        return entity.getNumberOfPlayers() < MAX_LIMIT_PLAYERS;
    }
    
    private boolean checkMatchStillGoing(MatchEntity entity) {
        return entity.getDuration() > 0L;
    }
    
    private boolean checkPreConditionalsToEnterInExistingMatch(MatchEntity entity) {
        return checkPlayersLimitByMatch(entity) && checkMatchStillGoing(entity);
    }
    
}
