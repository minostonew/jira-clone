package pl.test.jiraClone.RESTapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.test.jiraClone.RESTapi.model.Team;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {
    Optional<Team> findByName(String name);
}
