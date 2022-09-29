package toy.toymvc.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberInfo {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
