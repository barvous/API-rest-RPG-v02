package br.com.grimoire.apirestrpgv02.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.grimoire.apirestrpgv02.model.Personagem;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class PersonagemRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PersonagemRepository personagemRepository;

    void createPersonagens() {
        Personagem personagem1 = new Personagem(null, "Marcos Daniel", "Thorfinn Silverskin", "Órfão", "Bruxo", 20, 8,
                14,
                10, 14, 17);
        entityManager.persist(personagem1);

        Personagem personagem2 = new Personagem(null, "Ruan Jantorno", "Holdornn Silverskin", "Órfão", "Bárbaro", 20,
                17,
                14, 8, 14, 10);
        entityManager.persist(personagem2);
    }

    @Test
    @DisplayName("Should get a List of Personagem successfully from DB")
    void findByNomeJogadorContainingCaseExist() {

        createPersonagens();

        List<Personagem> listaPersonagem = personagemRepository.findByNomeJogadorContaining("Marcos");

        assertThat(!listaPersonagem.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should get a Empty List of Personagem from DB")
    void findByNomeJogadorContainingCaseEmpty() {

        List<Personagem> listaPersonagem = personagemRepository.findByNomeJogadorContaining("Marcos");

        assertThat(listaPersonagem.isEmpty()).isTrue();
    }

    @Test
    void findByNomePersonagemContaining() {

    }

}
