package pl.test.jiraClone.RESTapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.test.jiraClone.RESTapi.dto.TaskRequest;
import pl.test.jiraClone.RESTapi.dto.TaskResponse;
import pl.test.jiraClone.RESTapi.model.Task;
import pl.test.jiraClone.RESTapi.model.Team;
import pl.test.jiraClone.RESTapi.model.User;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "taskRequest.description")
    @Mapping(target = "taskName", source = "taskRequest.taskName")
    Task mapForAdmin(TaskRequest taskRequest, Team team, User user);

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "taskRequest.description")
    @Mapping(target = "taskName", source = "taskRequest.taskName")
    @Mapping(target = "user.username", ignore = true)
    Task map(TaskRequest taskRequest, Team team, User user);


    @Mapping(target = "id", source = "taskId")
    @Mapping(target = "teamName", source = "team.name")
    @Mapping(target = "userName", source = "user.username")
    TaskResponse mapToDto(Task task);
}
