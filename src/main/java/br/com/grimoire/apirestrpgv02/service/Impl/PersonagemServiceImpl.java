package br.com.grimoire.apirestrpgv02.service.Impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.grimoire.apirestrpgv02.model.Personagem;
import br.com.grimoire.apirestrpgv02.model.dto.CadastroPersonagemDTO;
import br.com.grimoire.apirestrpgv02.model.exception.InternalServerException;
import br.com.grimoire.apirestrpgv02.model.exception.NotFoundException;
import br.com.grimoire.apirestrpgv02.repository.PersonagemRepository;
import br.com.grimoire.apirestrpgv02.service.PersonagemService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonagemServiceImpl implements PersonagemService {

    private final PersonagemRepository personagemRepository;

    @Override
    public List<Personagem> listarPersonagem() {
        return personagemRepository.findAll();
    }

    @Override
    public Personagem buscarPersonagem(Long idPersonagem) {
        return personagemRepository.findById(idPersonagem)
                .orElseThrow(() -> new NotFoundException("Falha ao procurar o personagem"));
    }

    @Override
    @Transactional
    public Personagem criarPersonagem(CadastroPersonagemDTO cadastroPersonagemDTO) {
        try {

            return personagemRepository.save(cadastroPersonagemDTO.convertToPersonagem());
        } catch (Exception e) {
            throw new InternalServerException("Falha ao criar o personagem no banco de dados");
        }
    }

    @Override
    @Transactional
    public void atualizarPersonagem(CadastroPersonagemDTO cadastroPersonagemDTO, Long idPersonagem) {
        try {
            Personagem personagemDB = buscarPersonagem(idPersonagem);

            BeanUtils.copyProperties(cadastroPersonagemDTO, personagemDB);

            personagemRepository.save(personagemDB);
        } catch (Exception e) {
            throw new InternalServerException("Falha ao atualizar o personagem no banco de dados");
        }
    }

    @Override
    @Transactional
    public void deletarPersonagem(Long idPersonagem) {
        try {
            Personagem personagemDB = buscarPersonagem(idPersonagem);

            personagemRepository.delete(personagemDB);
        } catch (Exception e) {
            throw new InternalServerException("Falha ao deletar o personagem no banco de dados");
        }
    }

}
