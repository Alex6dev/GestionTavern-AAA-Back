package aaa.tavern.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ModifNicknameDto {
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    @NotBlank(message = "Nom d'utilisateur obligatoire")
    private String nickName;

    protected ModifNicknameDto() {
    }

    public ModifNicknameDto(String email,
            @NotNull @Size(min = 8, max = 30) @NotBlank(message = "Nom d'utilisateur obligatoire") String nickName) {
        this.email = email;
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public String getNickName() {
        return nickName;
    }

}
