package br.com.grimoire.apirestrpgv02.service.Impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.grimoire.apirestrpgv02.model.Habilidade;
import br.com.grimoire.apirestrpgv02.model.Personagem;
import br.com.grimoire.apirestrpgv02.model.exception.BadRequestException;
import br.com.grimoire.apirestrpgv02.service.AcaoService;
import br.com.grimoire.apirestrpgv02.service.HabilidadeService;
import br.com.grimoire.apirestrpgv02.service.PersonagemService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AcaoServiceImpl implements AcaoService {

    private final static String TIPO_HABILIDADE_DANO = "DANO";
    private final static String TIPO_HABILIDADE_CURA = "CURA";

    private final HabilidadeService habilidadeService;
    private final PersonagemService personagemService;

    public String retiraNaoNumericos(String texto) {
        StringBuilder resultado = new StringBuilder();

        for (char c : texto.toCharArray()) {
            if (Character.isDigit(c)) {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }

    private void validaDanoHabilidade(Habilidade habilidade, Personagem personagemOrigem, Personagem personagemAlvo) {
        String tipoHabilidade = habilidade.getTipo();

        if (!tipoHabilidade.equals(TIPO_HABILIDADE_DANO)) {
            throw new BadRequestException("Esta habilidade não causa dano");
        }

        Integer custoHabilidade = Integer.parseInt(retiraNaoNumericos(habilidade.getCusto()));
        if (custoHabilidade > personagemOrigem.getPeAtual()) {
            throw new BadRequestException("O personagem de origem nao tem PE o suficiente para usar esta habilidade");
        }

    }

    @Override
    public void danoHabilidade(Long idHabilidade, Long idPersonagemOrigem, Long idPersonagemAlvo) {

        Habilidade habilidadeDB = habilidadeService.buscarHabilidade(idHabilidade);
        Personagem personagemOrigem = personagemService.buscarPersonagem(idPersonagemOrigem);
        Personagem personagemAlvo = personagemService.buscarPersonagem(idPersonagemAlvo);

        validaDanoHabilidade(habilidadeDB, personagemOrigem, personagemAlvo);

        //TODO: continuar a lógica para atualizar os status dos personagens e calcular o dano da habilidade
    }

    @Override
    public void curaHabilidade(Long idHabilidade, Long idPersonagemOrigem, Long idPersonagemAlvo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'curaHabilidade'");
    }
}
