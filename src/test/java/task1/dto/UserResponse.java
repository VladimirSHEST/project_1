package task1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String updatedAt;
}
