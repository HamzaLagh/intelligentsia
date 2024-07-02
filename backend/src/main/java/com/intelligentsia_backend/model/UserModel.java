package com.intelligentsia_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String email ;

    private String password;

    private Boolean enable;

    private String role;
}
