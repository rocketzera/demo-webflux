package io.github.rocketzera.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.rocketzera.webflux.domain.MatchDTO;
import io.github.rocketzera.webflux.service.IMatchService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/v1/match")
public class MatchController {

    @Autowired
    private IMatchService service;
    
    @GetMapping("mock")
    public Flux<MatchDTO> findAllMock() {
        return Flux.just(MatchDTO.builder().code("AAAA").build(), MatchDTO.builder().code("BBBB").build(), MatchDTO.builder().code("CCCC").build());
    }
    
    @GetMapping("mock/{code}")
    public Mono<MatchDTO> findOneMock(@PathVariable String code) {
        return Mono.just(MatchDTO.builder().code(code).build());
    }
    
    @PostMapping
    public Mono<MatchDTO> save(@RequestBody MatchDTO dto){
        return service.save(dto);
    }
    
    @PostMapping("{code}")
    public Mono<MatchDTO> enterInExistingMatch(@PathVariable String code){
        return service.enterInExistingMatch(code);
    }
    
    @GetMapping
    public Flux<MatchDTO> findAll() {
        return service.findAll();
    }
}
