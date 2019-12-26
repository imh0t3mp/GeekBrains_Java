package name.imh0t3mp.geekbrains.dto;

import javax.validation.constraints.NotNull;

public class AuthResponseDTO {
    @NotNull
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponseDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}