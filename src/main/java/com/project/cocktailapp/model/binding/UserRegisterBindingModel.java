package com.project.cocktailapp.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @NotBlank
    @NotNull
    @Size(min = 2,max = 20,message = "First name must be between 2 and 20 chars")
    private String firstName;

    private String lastName;

    @NotBlank
    @NotNull
    @Size(min = 3,max = 20,message = "Username must be between 3 and 20 chars")
    private String username;

    @NotBlank
    @NotNull
    @Size(min = 5,max = 20,message = "Password must be more then 5 chars")
    private String password;

    @NotBlank
    @NotEmpty
    private String confirmPassword;

    @NotNull(message = "Gender not selected")
    private String gender;

    @NotBlank
    @NotNull
    @Email(message = "Invalid email address")
    private String email;

    private String imgUrl;

}
