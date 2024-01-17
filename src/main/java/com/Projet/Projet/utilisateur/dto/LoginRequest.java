package com.Projet.Projet.utilisateur.dto;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
//    private String email;

    private String telephoneOrEmail;

    @NotBlank
    private String password;

    public String getTelephoneOrEmail() {
        return telephoneOrEmail;
    }

    public void SetTelephoneOrEmail(String telephoneOrEmail) {
        this.telephoneOrEmail = telephoneOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}