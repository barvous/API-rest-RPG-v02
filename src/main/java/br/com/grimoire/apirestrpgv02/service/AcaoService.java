package br.com.grimoire.apirestrpgv02.service;

public interface AcaoService {
    
    void danoHabilidade(Long idHabilidade, Long idPersonagemOrigem, Long idPersonagemAlvo);

    void curaHabilidade(Long idHabilidade, Long idPersonagemOrigem, Long idPersonagemAlvo);
}
