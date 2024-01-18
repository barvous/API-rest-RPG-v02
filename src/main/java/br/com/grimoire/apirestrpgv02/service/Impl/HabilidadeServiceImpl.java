package br.com.grimoire.apirestrpgv02.service.Impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.grimoire.apirestrpgv02.model.Habilidade;
import br.com.grimoire.apirestrpgv02.model.dto.CadastroHabilidadeDTO;
import br.com.grimoire.apirestrpgv02.model.exception.InternalServerException;
import br.com.grimoire.apirestrpgv02.model.exception.NotFoundException;
import br.com.grimoire.apirestrpgv02.repository.HabilidadeRepository;
import br.com.grimoire.apirestrpgv02.service.HabilidadeService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HabilidadeServiceImpl implements HabilidadeService {

    private final HabilidadeRepository habilidadeRepository;

    @Override
    public List<Habilidade> listarHabilidade() {
        return habilidadeRepository.findAll();
    }

    @Override
    public List<Habilidade> procurarHabilidade(String procura) {
        return habilidadeRepository.findByNomeOrDescricaoContaining(procura);
    }

    @Override
    public Habilidade buscarHabilidade(Long idHabilidade) {
        return habilidadeRepository.findById(idHabilidade)
                .orElseThrow(() -> new NotFoundException("Falha ao procurar a habilidade"));
    }

    @Override
    @Transactional
    public Habilidade criarHabilidade(CadastroHabilidadeDTO cadastroHabilidadeDTO) {

        try {

            return habilidadeRepository.save(cadastroHabilidadeDTO.convertToHabilidade());

        } catch (Exception e) {
            throw new InternalServerException("Falha ao salvar a habilidade no banco de dados");
        }

    }

    @Override
    @Transactional
    public void atualizarHabilidade(CadastroHabilidadeDTO cadastroHabilidadeDTO, Long idHabilidade) {
        Habilidade habilidadeDB = buscarHabilidade(idHabilidade);

        BeanUtils.copyProperties(cadastroHabilidadeDTO, habilidadeDB);

        try {
            habilidadeRepository.save(habilidadeDB);

        } catch (Exception e) {
            throw new InternalServerException("Falha ao atualizar a habilidade");
        }

    }

    @Override
    @Transactional
    public void excluirHabilidade(Long idHabilidade) {
        Habilidade habilidadeDB = buscarHabilidade(idHabilidade);

        try {

            habilidadeRepository.delete(habilidadeDB);

        } catch (Exception e) {
            throw new InternalServerException("Falha ao deletar a habilidade");
        }
    }

}
