package pl.test.jiraClone.RESTapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.test.jiraClone.RESTapi.dto.CommentDto;
import pl.test.jiraClone.RESTapi.service.CommentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CommentDto commentDto)
    {
        commentService.createPost(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/by-task/{taskId}")
    public List<CommentDto> getCommentsByTask(Long taskId)
    {
        return commentService.getCommentsByTask(taskId);
    }
    @GetMapping("/by-user/{taskId}")
    public List<CommentDto> getCommentsByUser(Long userId)
    {
        return commentService.getCommentsByUser(userId);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editComment(@RequestBody CommentDto commentDto)
    {
        commentService.editComment(commentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
