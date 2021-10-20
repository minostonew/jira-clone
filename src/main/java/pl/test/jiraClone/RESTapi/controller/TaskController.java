package pl.test.jiraClone.RESTapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.test.jiraClone.RESTapi.dto.TaskRequest;
import pl.test.jiraClone.RESTapi.dto.TaskResponse;
import pl.test.jiraClone.RESTapi.service.TaskService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TaskRequest taskRequest)
    {
        taskService.save(taskRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/admin")
    public ResponseEntity<Void> saveForAdmin(@RequestBody TaskRequest taskRequest)
    {
        taskService.saveForAdmin(taskRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("by-team/{id}")
    public ResponseEntity<List<TaskResponse>> getTasksByTeam(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByTeam(id));
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<TaskResponse>> getTasksByUser(String name)
    {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByUser(name));
    }
    @GetMapping("/my-tasks")
    public ResponseEntity<List<TaskResponse>> getMyTasks()
    {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getMyTasks());
    }

    @PutMapping
    public ResponseEntity<String> editTask(@RequestBody TaskRequest taskRequest)
    {
        taskService.editTask(taskRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long taskId)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
