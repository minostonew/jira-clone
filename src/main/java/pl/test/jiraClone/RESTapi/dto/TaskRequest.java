package pl.test.jiraClone.RESTapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private Long taskId;
    private String taskName;
    private String teamName;
    private String url;
    private String description;
    private String username;
}
