package aaa.tavern.dto;

public class ModifPasswordDto {
    String email;
    String password;

    protected ModifPasswordDto() {
    }

    public ModifPasswordDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
