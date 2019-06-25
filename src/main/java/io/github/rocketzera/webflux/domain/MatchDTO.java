package io.github.rocketzera.webflux.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {

    public MatchDTO(MatchEntity entity) {
        this.code = entity.getCode();
        this.duration = entity.getDuration();
        this.numberOfPlayers = entity.getNumberOfPlayers();
        this.typeOfMatch = entity.getTypeOfMatch();
        this.description = entity.getDescription();
    }
    
    private String code;

    private Long duration;
    
    private Integer numberOfPlayers;

    private String typeOfMatch;
    
    private String description;
    
}
