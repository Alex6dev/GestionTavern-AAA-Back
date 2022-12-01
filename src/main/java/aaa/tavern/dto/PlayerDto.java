package aaa.tavern.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import aaa.tavern.dto.annotation.PasswordMatches;
import aaa.tavern.dto.annotation.ValidEmail;

@PasswordMatches
public class PlayerDto {
    @ValidEmail
    @NotNull
    @NotEmpty(message = "E-mail obligatoire")
    @Email
    private String email;

    @NotNull
    @Size(min = 8, max = 30)
    @NotBlank(message = "Nom d'utilisateur obligatoire")
    private String nickname;

    @NotNull
    @NotBlank(message = "Mot de passe obligatoire")
    private String password;
    private String matchingPassword;

    protected PlayerDto() {
    }

    public PlayerDto(String email, String nickname, String password, String matchingPassword) {
        this.email = email.toLowerCase();
        this.nickname = nickname;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
