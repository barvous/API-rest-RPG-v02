package br.com.grimoire.apirestrpgv02.model.dto;

import org.springframework.beans.BeanUtils;

import br.com.grimoire.apirestrpgv02.model.Personagem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroPersonagemDTO {

    private String nomeJogador;

    private String nomePersonagem;

    private String origem;

    private String classe;

    private String nex;

    private Integer atributoForca;

    private Integer atributoAgilidade;

    private Integer atributoIntelecto;

    private Integer atributoVigor;

    private Integer atributoPresenca;

    // private List<Habilidade> listaHabilidade;


    public Personagem convertToPersonagem(){
        Personagem personagem = new Personagem();

        BeanUtils.copyProperties(this, personagem);

        return personagem;
    }
}
