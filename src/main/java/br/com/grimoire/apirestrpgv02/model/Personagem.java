package br.com.grimoire.apirestrpgv02.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERSONAGEM")
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSONAGEM")
    private Long id;

    @Column(name = "NOME_JOGADOR")
    private String nomeJogador;

    @Column(name = "NOME_PERSONAGEM")
    private String nomePersonagem;

    @Column(name = "ORIGEM_PERSONAGEM")
    private String origem;

    @Column(name = "CLASSE_PERSONAGEM")
    private String classe;

    @Column(name = "NEX")
    private Integer nex;

    @Column(name = "ATRIBUTO_FORCA", nullable = false)
    private Integer atributoForca;

    @Column(name = "ATRIBUTO_AGILIDADE", nullable = false)
    private Integer atributoAgilidade;

    @Column(name = "ATRIBUTO_INTELECTO", nullable = false)
    private Integer atributoIntelecto;

    @Column(name = "ATRIBUTO_VIGOR", nullable = false)
    private Integer atributoVigor;

    @Column(name = "ATRIBUTO_PRESENCA", nullable = false)
    private Integer atributoPresenca;

}
