package br.com.grimoire.apirestrpgv02.service;

import java.util.List;

import br.com.grimoire.apirestrpgv02.model.Personagem;
import br.com.grimoire.apirestrpgv02.model.dto.CadastroPersonagemDTO;

public interface PersonagemService {

    List<Personagem> listarPersonagem();

    List<Personagem> procurarPersonagemNomePersonagem(String procuraNomePersonagem);

    List<Personagem> procurarPersonagemNomeJogador(String procuraNomeJogador);

    Personagem buscarPersonagem(Long idPersonagem);

    Personagem criarPersonagem(CadastroPersonagemDTO cadastroPersonagemDTO);

    void atualizarPersonagem(CadastroPersonagemDTO cadastroPersonagemDTO, Long idPersonagem);

    void deletarPersonagem(Long idPersonagem);

}
