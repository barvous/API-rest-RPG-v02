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
@Table(name = "HABILIDADE")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HABILIDADE", nullable = false)
    private Long id;

    @Column(name = "NOME_HABILIDADE", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO_HABILIDADE", nullable = false, length = 500)
    private String descricao;

    @Column(name = "TIPO_HABILIDADE", nullable = false)
    private String tipo;

    @Column(name = "CUSTO")
    private String custo;

    @Column(name = "VALOR_HABILIDADE")
    private String valor;

}
