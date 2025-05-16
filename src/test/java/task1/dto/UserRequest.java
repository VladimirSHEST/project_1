package task1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {
    private String name;
    private String job;

    public UserRequest(String name, String job){
        this.name = name;
        this.job = job;
    }
}
