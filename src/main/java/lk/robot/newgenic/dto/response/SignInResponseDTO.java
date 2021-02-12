package lk.robot.newgenic.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class SignInResponseDTO {

    private String token;
    private String userId;
    private String username;
    private LocalDate loginDate;
    private LocalTime loginTime;

    public SignInResponseDTO() {
    }

    public SignInResponseDTO(String token,
                             String userId,
                             String username,
                             LocalDate loginDate,
                             LocalTime loginTime) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.loginDate = loginDate;
        this.loginTime = loginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDate loginDate) {
        this.loginDate = loginDate;
    }

    public LocalTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalTime loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "SignInResponseDTO{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", loginDate=" + loginDate +
                ", loginTime=" + loginTime +
                '}';
    }
}
