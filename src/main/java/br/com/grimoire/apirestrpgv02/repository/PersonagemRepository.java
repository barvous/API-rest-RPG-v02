package br.com.grimoire.apirestrpgv02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.grimoire.apirestrpgv02.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {


    List<Personagem> findByNomePersonagemContaining(String procuraNomePersonagem);

    List<Personagem> findByNomeJogadorContaining(String procuraNomeJogador);
}
