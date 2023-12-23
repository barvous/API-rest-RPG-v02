package br.com.grimoire.apirestrpgv02.server;

import java.util.List;

import br.com.grimoire.apirestrpgv02.model.Personagem;

public interface PersonagemService {

    Personagem salvarPersonagem(Personagem personagem);

    List<Personagem> listarPersonagens();

    Personagem buscarPersonagem(Long idPersonagem);

    void excluirPersonagem(Long idPersonagem);

}
