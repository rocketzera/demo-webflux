package io.github.rocketzera.webflux.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class MatchEntity {

    public MatchEntity(MatchDTO dto) {
        this.duration = dto.getDuration();
        this.numberOfPlayers = dto.getNumberOfPlayers();
        this.typeOfMatch = dto.getTypeOfMatch();
        this.description = dto.getDescription();
    }

    @Id
    private String code;

    private Long duration;
    
    private Integer numberOfPlayers;

    private String typeOfMatch;
    
    private String description;
    
}
