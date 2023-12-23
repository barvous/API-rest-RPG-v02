package br.com.grimoire.apirestrpgv02.service;

import java.util.List;

import br.com.grimoire.apirestrpgv02.model.Habilidade;

public interface HabilidadeService {

    List<Habilidade> listarHabilidade();

    Habilidade buscarHabilidade(Long id);

    Habilidade salvarHabilidade(Habilidade habilidade);

    void excluirHabilidade(Long id);

}
