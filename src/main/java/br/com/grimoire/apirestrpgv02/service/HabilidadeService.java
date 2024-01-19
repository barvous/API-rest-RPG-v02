package br.com.grimoire.apirestrpgv02.service;

import java.util.List;

import br.com.grimoire.apirestrpgv02.model.Habilidade;
import br.com.grimoire.apirestrpgv02.model.dto.CadastroHabilidadeDTO;

public interface HabilidadeService {

    List<Habilidade> listarHabilidade();

    List<Habilidade> procurarHabilidadeNome(String procuraNome);

    List<Habilidade> procurarHabilidadeDescricao(String procuraDescricao);

    Habilidade buscarHabilidade(Long idHabilidade);

    Habilidade criarHabilidade(CadastroHabilidadeDTO cadastroHabilidadeDTO);

    void atualizarHabilidade(CadastroHabilidadeDTO cadastroHabilidadeDTO, Long idHabilidade);

    void excluirHabilidade(Long idHabilidade);

}
