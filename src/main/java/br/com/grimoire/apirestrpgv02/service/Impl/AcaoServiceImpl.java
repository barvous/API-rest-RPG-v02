package br.com.grimoire.apirestrpgv02.service.Impl;

import java.util.Random;

import org.springframework.stereotype.Service;

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
    private final static String SPLIT_VALOR_DADO = "d";

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

    private void validaHabilidadeDano(Habilidade habilidade) {
        String tipoHabilidade = habilidade.getTipo();

        if (!tipoHabilidade.equals(TIPO_HABILIDADE_DANO)) {
            throw new BadRequestException("Esta habilidade não causa dano");
        }
    }

    private void validaHabilidadeCura(Habilidade habilidade) {
        String tipoHabilidade = habilidade.getTipo();

        if (!tipoHabilidade.equals(TIPO_HABILIDADE_CURA)) {
            throw new BadRequestException("Esta habilidade não tem efeito de cura");
        }
    }

    private Integer calculaValorDado(String dado) {
        String[] valoresDado = dado.split(SPLIT_VALOR_DADO);

        Integer quantidadeDeDados = Integer.parseInt(valoresDado[0]);
        Integer valorDado = Integer.parseInt(valoresDado[1]);

        Integer danoTotal = 0;

        for (int i = 1; i <= quantidadeDeDados; i++) {
            Random r = new Random();
            Integer valorAleatorio = r.nextInt(1, (valorDado + 1));
            danoTotal += valorAleatorio;
        }
        return danoTotal;
    }

    private Integer calculaPvAtualPersonagemAlvo(Personagem personagemAlvo, Integer valorHabilidade,
            String tipoCalculo) {

        Integer vidaAtual = personagemAlvo.getPvAtual();
        Integer vidaMaxima = personagemAlvo.getPvMaximo();

        switch (tipoCalculo) {
            case TIPO_HABILIDADE_DANO:

                if (vidaAtual <= 0) {
                    throw new BadRequestException(
                            "Não é possível dar mais dano neste personagem pois ele já está morrendo.");
                }

                vidaAtual -= valorHabilidade;

                if (vidaAtual < 0) {
                    return 0;
                }

                break;

            case TIPO_HABILIDADE_CURA:

                vidaAtual += valorHabilidade;

                if (vidaAtual > vidaMaxima) {
                    return vidaMaxima;
                }
                break;

            default:
                throw new BadRequestException("Tipo de habilidade desconhecido");
        }

        return vidaAtual;
    }

    private Integer calculaPePersonagemOrigem(Personagem personagemOrigem, Habilidade habilidade) {

        Integer peAtual = personagemOrigem.getPeAtual();

        Integer custoHabilidade = Integer.parseInt(retiraNaoNumericos(habilidade.getCusto()));
        if (custoHabilidade > peAtual) {
            throw new BadRequestException("O personagem de origem nao tem PE o suficiente para usar esta habilidade");
        }

        peAtual -= custoHabilidade;

        return peAtual;
    }

    @Override
    public void danoHabilidade(Long idHabilidade, Long idPersonagemOrigem, Long idPersonagemAlvo) {

        Habilidade habilidade = habilidadeService.buscarHabilidade(idHabilidade);
        Personagem personagemOrigem = personagemService.buscarPersonagem(idPersonagemOrigem);
        Personagem personagemAlvo = personagemService.buscarPersonagem(idPersonagemAlvo);

        validaHabilidadeDano(habilidade);

        Integer danoHabilidade = calculaValorDado(habilidade.getValor());

        Integer peAtualPersonagemOrigem = calculaPePersonagemOrigem(personagemOrigem, habilidade);
        personagemOrigem.setPeAtual(peAtualPersonagemOrigem);

        Integer pvAtualPersonagemAlvo = calculaPvAtualPersonagemAlvo(personagemAlvo, danoHabilidade,
                TIPO_HABILIDADE_DANO);
        personagemAlvo.setPvAtual(pvAtualPersonagemAlvo);

        personagemService.atualizarPersonagem(personagemOrigem, idPersonagemOrigem);
        personagemService.atualizarPersonagem(personagemAlvo, idPersonagemAlvo);
    }

    @Override
    public void curaHabilidade(Long idHabilidade, Long idPersonagemOrigem, Long idPersonagemAlvo) {

        Habilidade habilidade = habilidadeService.buscarHabilidade(idHabilidade);
        Personagem personagemOrigem = personagemService.buscarPersonagem(idPersonagemOrigem);
        Personagem personagemAlvo = personagemService.buscarPersonagem(idPersonagemAlvo);

        validaHabilidadeCura(habilidade);

        Integer curaHabilidade = calculaValorDado(habilidade.getValor());

        Integer peAtualPersonagemOrigem = calculaPePersonagemOrigem(personagemOrigem, habilidade);
        personagemOrigem.setPeAtual(peAtualPersonagemOrigem);

        Integer pvAtualPersonagemAlvo = calculaPvAtualPersonagemAlvo(personagemAlvo, curaHabilidade,
                TIPO_HABILIDADE_CURA);
        personagemAlvo.setPvAtual(pvAtualPersonagemAlvo);

        personagemService.atualizarPersonagem(personagemOrigem, idPersonagemOrigem);
        personagemService.atualizarPersonagem(personagemAlvo, idPersonagemAlvo);
    }
}
