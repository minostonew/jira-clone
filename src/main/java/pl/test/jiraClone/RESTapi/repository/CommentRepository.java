package pl.test.jiraClone.RESTapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.test.jiraClone.RESTapi.model.Comment;
import pl.test.jiraClone.RESTapi.model.Task;
import pl.test.jiraClone.RESTapi.model.User;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> findByTask(Task task);

    List<Comment> findByUser(User user);
}
