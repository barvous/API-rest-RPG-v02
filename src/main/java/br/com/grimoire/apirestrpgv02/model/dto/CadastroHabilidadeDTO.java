package br.com.grimoire.apirestrpgv02.model.dto;

import org.springframework.beans.BeanUtils;

import br.com.grimoire.apirestrpgv02.model.Habilidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroHabilidadeDTO {

    private String nome;

    private String descricao;

    private String tipo;

    private String custo;

    private String valor;
    

    public Habilidade convertToHabilidade(){
        Habilidade habilidade = new Habilidade();

        BeanUtils.copyProperties(this, habilidade);

        return habilidade;
    }
}
