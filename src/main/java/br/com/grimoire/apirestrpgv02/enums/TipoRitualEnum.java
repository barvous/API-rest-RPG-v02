package br.com.grimoire.apirestrpgv02.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoRitualEnum {
    CONHECIMENTO("Conhecimento"),
    SANGUE("Sangue"),
    MORTE("Morte"),
    ENERGIA("Energia");

    private String tipoRitual;
}
