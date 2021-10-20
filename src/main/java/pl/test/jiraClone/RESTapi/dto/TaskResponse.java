package pl.test.jiraClone.RESTapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Long id;
    private String taskName;
    private String url;
    private String description;
    private String userName;
    private String teamName;
}
