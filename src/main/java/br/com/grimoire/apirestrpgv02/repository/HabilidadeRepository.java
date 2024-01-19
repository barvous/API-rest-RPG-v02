package br.com.grimoire.apirestrpgv02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.grimoire.apirestrpgv02.model.Habilidade;

public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {

    List<Habilidade> findByNomeContaining(String procuraNomeHabilidade);

    List<Habilidade> findByDescricaoContaining(String procuraDescricaoHabilidade);
}
