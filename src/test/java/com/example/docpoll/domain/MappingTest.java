package com.example.docpoll.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=PostgreSQL",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.jpa.show-sql=true"
})
class PollMappingTest {
    //Test for the mapping of the classes, test fail means classes broke somehow
    @PersistenceContext
    private EntityManager em;

    @Test
    void canPersistAndReadPollGraph() {
        User user = new User();
        user.setUsername("Donald");

        Poll poll = new Poll();
        poll.setQuestion("Bipbapbop");
        poll.setCreator(user);

        VoteOption o1 = new VoteOption();
        o1.setCaption("Bap");

        VoteOption o2 = new VoteOption();
        o2.setCaption("Bip");

        poll.addVoteOption(o1);
        poll.addVoteOption(o2);

        Vote v = new Vote();
        v.setUser(user);
        v.setChosenOption(o1);

        em.persist(user);
        em.persist(poll);
        em.persist(o1);
        em.persist(o2);
        em.persist(v);
        em.flush();
        em.clear();

        Poll saved = em.find(Poll.class, poll.getPollId());
        assertNotNull(saved);
        assertEquals("Bipbapbop", saved.getQuestion());

        List<?> options = em.createQuery(
                        "select o from VoteOption o where o.poll.id = :pid")
                .setParameter("pid", saved.getPollId())
                .getResultList();
        assertEquals(2, options.size());
    }
}
