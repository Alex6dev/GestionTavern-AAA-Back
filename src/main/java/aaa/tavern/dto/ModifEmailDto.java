package aaa.tavern.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import aaa.tavern.dto.annotation.ValidEmail;

public class ModifEmailDto {

    private String emailPlayer;

    @ValidEmail
    @NotNull
    @NotEmpty(message = "E-mail obligatoire")
    @Email
    private String emailModified;

    protected ModifEmailDto() {

    }

    public String getEmailPlayer() {
        return emailPlayer;
    }

    public String getEmailModified() {
        return emailModified;
    }

}
