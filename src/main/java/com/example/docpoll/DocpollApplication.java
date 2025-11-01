package com.example.docpoll;

import com.example.docpoll.domain.Poll;
import com.example.docpoll.domain.User;
import com.example.docpoll.domain.Vote;
import com.example.docpoll.domain.VoteOption;
import com.example.docpoll.repository.PollRepository;
import com.example.docpoll.repository.UserRepository;
import com.example.docpoll.repository.VoteOptionRepository;
import com.example.docpoll.repository.VoteReporistory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DocpollApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocpollApplication.class, args);
	}

    @Bean
    CommandLineRunner seed(
            PollRepository polls,
            VoteOptionRepository options,
            VoteReporistory votes,
            UserRepository users
    ) {
        return args -> {
            if (polls.count() > 0) return;

            users.save(new User("user1"));
            users.save(new User("user2"));
            users.save(new User("user3"));

            // Poll
            var poll = new Poll();
            poll.setQuestion("Example polll");
            poll = polls.save(poll);

            // Options
            var optA = new VoteOption();
            optA.setCaption("A");
            optA = options.save(optA);

            var optB = new VoteOption();
            optB.setCaption("B");
            optB = options.save(optB);

            var optC = new VoteOption();
            optC.setCaption("C");
            optC = options.save(optC);

            poll.addVoteOption(optA);
            poll.addVoteOption(optB);
            poll.addVoteOption(optC);

            Vote v1 = new Vote(); v1.setChosenOption(optA); v1.setUser(new User("user1"));
            Vote v2 = new Vote(); v2.setChosenOption(optB); v2.setUser(new User("user2"));
            Vote v3 = new Vote(); v3.setChosenOption(optB); v3.setUser(new User("user3"));

            votes.save(v1);
            votes.save(v2);
            votes.save(v3);
        };
    }
}
