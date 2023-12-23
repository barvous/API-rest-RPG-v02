package br.com.grimoire.apirestrpgv02.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.grimoire.apirestrpgv02.model.Habilidade;
import br.com.grimoire.apirestrpgv02.model.dto.CadastroHabilidadeDTO;
import br.com.grimoire.apirestrpgv02.service.HabilidadeService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/habilidades")// plural no endpoint (Padrão REST)
public class HabilidadeController {

    private final HabilidadeService habilidadeService; // Injeção de dependencia com LOMBOK

    @PostMapping()
    public ResponseEntity<Long> criarHabilidade(@RequestBody CadastroHabilidadeDTO cadastroHabilidadeDTO) {

        Long idHabilidade = habilidadeService.criarHabilidade(cadastroHabilidadeDTO).getId();

        return ResponseEntity.ok().body(idHabilidade);
    }

    @GetMapping()
    public ResponseEntity<List<Habilidade>> listarHabilidades() {

        List<Habilidade> habilidades = habilidadeService.listarHabilidade();

        return ResponseEntity.ok().body(habilidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidade> buscarHabilidade(@PathVariable Long id) {

        Habilidade habilidade = habilidadeService.buscarHabilidade(id);

        return ResponseEntity.status(200).body(habilidade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Habilidade> excluirHabilidade(@PathVariable Long id) {

        habilidadeService.excluirHabilidade(id);

        return ResponseEntity.status(200).build();
    }
}
