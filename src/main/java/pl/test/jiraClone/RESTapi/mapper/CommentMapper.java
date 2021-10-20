package pl.test.jiraClone.RESTapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.test.jiraClone.RESTapi.dto.CommentDto;
import pl.test.jiraClone.RESTapi.model.Comment;
import pl.test.jiraClone.RESTapi.model.Task;
import pl.test.jiraClone.RESTapi.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "task", source = "task")
    Comment map(CommentDto commentDto, User user, Task task);


    @Mapping(target = "taskId", expression = "java(comment.getTask().getTaskId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    CommentDto mapCommentToDto(Comment comment);
}
