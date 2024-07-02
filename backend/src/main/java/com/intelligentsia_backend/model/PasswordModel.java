package com.intelligentsia_backend.model;

import lombok.Data;

@Data
public class PasswordModel {
    private Long idUser;
    private String email;
    private String oldPassword;
    private String newPassword;

}
