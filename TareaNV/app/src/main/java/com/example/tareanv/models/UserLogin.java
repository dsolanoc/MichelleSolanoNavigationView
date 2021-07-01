package com.example.tareanv.models;

public class UserLogin {
    private String username;
    private String email;
    private String password;
    private String foto;

    public UserLogin(String username, String email, String password, String foto){
        this.username=username;
        this.email=email;
        this.password=password;
        this.foto=foto;

    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
