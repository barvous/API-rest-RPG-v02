package br.com.grimoire.apirestrpgv02.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grimoire.apirestrpgv02.model.Personagem;
import br.com.grimoire.apirestrpgv02.model.dto.CadastroPersonagemDTO;
import br.com.grimoire.apirestrpgv02.service.PersonagemService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/personagens")
public class PersonagemController {

    private final PersonagemService personagemService;

    @GetMapping()
    public ResponseEntity<List<Personagem>> listarPersonagens() {

        List<Personagem> listaPersonagens = personagemService.listarPersonagem();

        return ResponseEntity.ok().body(listaPersonagens);
    }

    @GetMapping(path = "/{idPersonagem}")
    public ResponseEntity<Personagem> buscarPersonagem(@PathVariable Long idPersonagem) {

        Personagem personagem = personagemService.buscarPersonagem(idPersonagem);

        return ResponseEntity.ok().body(personagem);
    }

    @PostMapping
    public ResponseEntity<Long> criarPersonagem(@RequestBody CadastroPersonagemDTO cadastroPersonagemDTO) {
        Long idPersonagem = personagemService.criarPersonagem(cadastroPersonagemDTO).getId();

        return ResponseEntity.ok().body(idPersonagem);
    }

    @PutMapping(path = "/{idPersonagem}")
    public ResponseEntity<Long> atualizar(@RequestBody CadastroPersonagemDTO cadastroPersonagemDTO,@PathVariable Long idPersonagem) {

        personagemService.atualizarPersonagem(cadastroPersonagemDTO, idPersonagem);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{idPersonagem}")
    public ResponseEntity<Personagem> deletarPersonagem(@PathVariable Long idPersonagem) {

        personagemService.deletarPersonagem(idPersonagem);

        return ResponseEntity.noContent().build();
    }

}
