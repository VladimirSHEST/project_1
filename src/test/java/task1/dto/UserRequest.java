package task1.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String job;

    public UserRequest(String name, String job){
        this.name = name;
        this.job = job;
    }
}
