package br.com.grimoire.apirestrpgv02.server.Impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.grimoire.apirestrpgv02.model.Habilidade;
import br.com.grimoire.apirestrpgv02.model.exception.BadRequestException;
import br.com.grimoire.apirestrpgv02.model.exception.NotFoundException;
import br.com.grimoire.apirestrpgv02.repository.HabilidadeRepository;
import br.com.grimoire.apirestrpgv02.server.HabilidadeService;

@Service
public class HabilidadeServiceImpl implements HabilidadeService {

    private final HabilidadeRepository habilidadeRepository;

    // A injeção de dependência é feita através do construtor pois o objetos são
    // 'final'.
    // Isso é feito para garantir que haverá somente UMA instância de cada classe
    // que possa utilizar da Injeção de Dependência
    HabilidadeServiceImpl(HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
    }

    @Override
    public List<Habilidade> listarHabilidade() {
        return habilidadeRepository.findAll();
    }

    @Override
    public Habilidade buscarHabilidade(Long id) {
        return habilidadeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado nenhuma habilidade com o id " + id));
    }

    @Override
    @Transactional // A anotação Transactional do springframework permite que as funções façam
                   // rollbacks no bancos de dados
                   // caso haja alguma falha dentro da função.
    public Habilidade salvarHabilidade(Habilidade habilidade) {

        if (habilidade.getId() != null) {
            Long idHabilidade = habilidade.getId();
            Habilidade habilidadeBanco = buscarHabilidade(idHabilidade);

            // VERIFICA SE ALGUM CAMPO É NULO, E CASO FOR NULO, ELE NÃO SOFRE ALTERAÇÕES
            habilidade = verificarAtualizacaoHabilidade(habilidade, habilidadeBanco);

            // AS ALTERAÇÕES SÃO COPIADAS PARA A HABILIDADE ALVO
            BeanUtils.copyProperties(habilidade, habilidadeBanco);
        }
        validarHabilidade(habilidade);

        return habilidadeRepository.save(habilidade);

    }

    @Override
    public void excluirHabilidade(Long id) {
        try {
            habilidadeRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new BadRequestException("Não foi encontrado nenhuma habilidade com o id " + id);
        }
    }

    // VERIFICA SE ALGUM CAMPO É NULO, E CASO FOR NULO, ELE NÃO SOFRE ALTERAÇÕES
    public Habilidade verificarAtualizacaoHabilidade(Habilidade habilidadeAlteracoes, Habilidade habilidadeAlvo) {
        // CASO O NOME SEJA NULO, ELE NÃO SOFRE ALTERAÇÕES
        if (habilidadeAlteracoes.getNome() == null)
            habilidadeAlteracoes.setNome(habilidadeAlvo.getNome());

        if (habilidadeAlteracoes.getDescricao() == null)
            habilidadeAlteracoes.setDescricao(habilidadeAlvo.getDescricao());

        if (habilidadeAlteracoes.getTipo() == null)
            habilidadeAlteracoes.setTipo(habilidadeAlvo.getTipo());

        if (habilidadeAlteracoes.getCusto() == null)
            habilidadeAlteracoes.setCusto(habilidadeAlvo.getCusto());

        if (habilidadeAlteracoes.getValor() == null)
            habilidadeAlteracoes.setValor(habilidadeAlvo.getValor());

        return habilidadeAlteracoes;
    }

    // VERIFICA SE OS CAMPOS PRINCIPAIS ESTÃO NULOS
    public void validarHabilidade(Habilidade habilidade) {
        if (habilidade.getNome() == null || habilidade.getTipo() == null || habilidade.getDescricao() == null)
            throw new BadRequestException(
                    "Requisição inválida. Os campos: nome, tipo e descrição não podem ser nulos.");
    }
}