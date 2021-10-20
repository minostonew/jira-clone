package pl.test.jiraClone.RESTapi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.test.jiraClone.RESTapi.dto.TaskRequest;
import pl.test.jiraClone.RESTapi.dto.TaskResponse;
import pl.test.jiraClone.RESTapi.exceptions.SpringTodoException;
import pl.test.jiraClone.RESTapi.mapper.TaskMapper;
import pl.test.jiraClone.RESTapi.model.Task;
import pl.test.jiraClone.RESTapi.model.Team;
import pl.test.jiraClone.RESTapi.model.User;
import pl.test.jiraClone.RESTapi.repository.TaskRepository;
import pl.test.jiraClone.RESTapi.repository.TeamRepository;
import pl.test.jiraClone.RESTapi.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class TaskService {

    private final TeamRepository teamRepository;
    private final AuthService authService;
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    public Task save(TaskRequest taskRequest)
    {
        Team team = teamRepository.findByName(taskRequest.getTeamName()).orElseThrow(()-> new SpringTodoException("Cannot found team by name "+taskRequest.getTeamName()));
        User user = authService.getCurrentUser();
        Task save = taskRepository.save(taskMapper.map(taskRequest, team, user));
        save.setTeam(team);
        save.setUser(user);
        return save;
    }


    @Transactional
    public Task saveForAdmin(TaskRequest taskRequest)
    {
        Team team = teamRepository.findByName(taskRequest.getTeamName()).orElseThrow(()-> new SpringTodoException("Cannot found team by name "+taskRequest.getTeamName()));
        User user = userRepository.findByUsername(taskRequest.getUsername()).orElseThrow(() -> new SpringTodoException("No such user found"));
        Task save = taskRepository.save(taskMapper.mapForAdmin(taskRequest, team, user));
        save.setTeam(team);
        save.setUser(user);
        return save;
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalStateException(teamId.toString()));
        List<Task> tasks = taskRepository.findAllByTeam(team);
        return tasks.stream().map(taskMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByUser(String name)
    {
        User user = userRepository.findByUsername(name).orElseThrow(()-> new SpringTodoException("No such user as "+name));
        List<Task> tasks = taskRepository.findAllByUser(user);

        return tasks.stream()
                .map(taskMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getMyTasks()
    {
        User user = authService.getCurrentUser();
        List<Task> tasks = taskRepository.findAllByUser(user);
        return tasks
                .stream()
                .map(taskMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void editTask(TaskRequest taskRequest)
    {
        Task editedTask = taskRepository.findById(taskRequest.getTaskId()).orElseThrow(() -> new SpringTodoException("No such task found"));
        taskRepository.save(editedTask);
        editedTask.setTaskName(taskRequest.getTaskName());
        editedTask.setDescription(taskRequest.getDescription());
        editedTask.setUrl(taskRequest.getUrl());

    }


    public void deleteTask(Long taskId)
    {
        taskRepository.deleteById(taskId);
    }

}
