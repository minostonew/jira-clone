package pl.test.jiraClone.RESTapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.test.jiraClone.RESTapi.model.Task;
import pl.test.jiraClone.RESTapi.model.Team;
import pl.test.jiraClone.RESTapi.model.User;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByTeam(Team team);

    List<Task> findAllByUser(User user);
}
