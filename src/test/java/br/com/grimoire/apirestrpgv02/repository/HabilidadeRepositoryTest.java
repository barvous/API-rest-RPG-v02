package br.com.grimoire.apirestrpgv02.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

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

        Habilidade habilidade1 = new Habilidade(null, "Amaldiçoar Arma",
                "Você imbui sua arma com algum elemento escolhido (Morte, Sangue, Conhecimento ou Energia), a sua arma "
                        + "da +1d6 de dano extra deste elemento.",
                "Escolhido");
        entityManager.persist(habilidade1);

        Habilidade habilidade2 = new Habilidade(null, "Cicatrização",
                "Você acelera uma área específica do corpo de alguem em pouquissimo tempo, causando uma cicatrização" +
                        "forçada e extremamente acelerada. Você cura a pessoa em 2d8 + 4. A pessoa curada envelhece 1 ano de vida",
                "Morte");
        entityManager.persist(habilidade2);

    }

    @Test
    @DisplayName("Shoul get a List of Habilidade successfully from DB")
    void testFindByDescricaoContainingCaseSuccess() {

        createHabilidades();

        List<Habilidade> listaHabilidade = habilidadeRepository.findByDescricaoContaining("Você imbui sua arma");

        assertThat(!listaHabilidade.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Shoul get an empty List of Habilidade from DB")
    void testFindByDescricaoContainingCaseEmpty() {

        List<Habilidade> listaHabilidade = habilidadeRepository.findByDescricaoContaining("Você imbui sua arma");

        assertThat(listaHabilidade.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Shoul get a List of Habilidade successfully from DB")
    void testFindByNomeContainingCaseSuccess() {

        createHabilidades();

        List<Habilidade> listaHabilidade = habilidadeRepository.findByNomeContaining("Amaldi");

        assertThat(!listaHabilidade.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Shoul get an empty List of Habilidade from DB")
    void testFindByNomeContainingCaseEmpty() {
        List<Habilidade> listaHabilidade = habilidadeRepository.findByNomeContaining("Amaldi");

        assertThat(listaHabilidade.isEmpty()).isTrue();
    }
}
