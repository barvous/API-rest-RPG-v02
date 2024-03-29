package br.com.grimoire.apirestrpgv02.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.grimoire.apirestrpgv02.enums.TipoRitualEnum;
import br.com.grimoire.apirestrpgv02.model.Habilidade;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class HabilidadeRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    HabilidadeRepository habilidadeRepository;

    void createHabilidades() {

        Habilidade habilidade1 = new Habilidade(null, "Pressurização Bestial",
                "Você aumenta a sua pressão sanguínea, aumentando os batimentos cardíacos e o fluxo de adrenalina no seu corpo. Você ganha 2d4 de vida temporária durante a cena.",
                "Aprimoramento", TipoRitualEnum.SANGUE.getTipoRitual(), "1PE", "2d4");
        entityManager.persist(habilidade1);

        Habilidade habilidade2 = new Habilidade(null, "Sussurro sombrio",
                "O outro lado sussurra segredos para você sobre o mundo à sua volta. Você ganha vantagem nos seus testes de Percepção e Investigação.",
                "Aprimoramento", TipoRitualEnum.CONHECIMENTO.getTipoRitual(), "1PE", null);
        entityManager.persist(habilidade2);
    }

    @Test
    @DisplayName("Should get a List of Habilidades successfully from DB")
    void testFindByDescricaoContaining() {

        createHabilidades();

        List<Habilidade> listaHabilidade = habilidadeRepository.findByDescricaoContaining("2d4 de vida");

        assertThat(!listaHabilidade.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should get an Empty List of Habilidades from DB")
    void testFindByDescricaoContainingCaseEmpty() {

        List<Habilidade> listaHabilidade = habilidadeRepository.findByDescricaoContaining("2d4 de vida");

        assertThat(listaHabilidade.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should get a List of Habilidades successfully from DB")
    void testFindByNomeContaining() {

        createHabilidades();

        List<Habilidade> listaHabilidade = habilidadeRepository.findByNomeContaining("Sussurro");

        assertThat(!listaHabilidade.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should get an Empty List of Habilidades from DB")
    void testFindByNomeContainingCaseEmpty() {

        List<Habilidade> listaHabilidade = habilidadeRepository.findByNomeContaining("Sussurro");

        assertThat(listaHabilidade.isEmpty()).isTrue();
    }
}
