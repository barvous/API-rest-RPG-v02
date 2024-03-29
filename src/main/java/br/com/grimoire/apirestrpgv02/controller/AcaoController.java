package br.com.grimoire.apirestrpgv02.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.grimoire.apirestrpgv02.service.AcaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/acao")
@RequiredArgsConstructor
public class AcaoController {
    
    private final AcaoService acaoService;

    // Estes endpoints não estão seguindo os padrões REST, eles foram feitos assim apenas para utilização do acaoService
    // e este por sua vez, existe apenas para o estudo dos testes unitários

    @GetMapping(path = "/dano", params = {"idHabilidade", "idPersonagemOrigem", "idPersonagemAlvo"})
    public ResponseEntity<Void> acaoDanoHabilidade(@RequestParam Long idHabilidade, @RequestParam Long idPersonagemOrigem,
    @RequestParam Long idPersonagemAlvo){

        acaoService.danoHabilidade(idHabilidade, idPersonagemOrigem, idPersonagemAlvo);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/cura", params = {"idHabilidade", "idPersonagemOrigem", "idPersonagemAlvo"})
    public ResponseEntity<Void> curaDanoHabilidade(@RequestParam Long idHabilidade, @RequestParam Long idPersonagemOrigem,
    @RequestParam Long idPersonagemAlvo){

        acaoService.danoHabilidade(idHabilidade, idPersonagemOrigem, idPersonagemAlvo);

        return ResponseEntity.ok().build();
    }
}
