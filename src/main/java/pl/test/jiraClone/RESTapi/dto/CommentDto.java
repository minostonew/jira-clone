package pl.test.jiraClone.RESTapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long commentId;
    private Long taskId;
    private String text;
    private Instant createdDate;
    private String username;
}
