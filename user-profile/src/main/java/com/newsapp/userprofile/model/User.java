package com.newsapp.userprofile.model;

import com.newsapp.userprofile.exception.CustomValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(
        name = "users"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @SequenceGenerator(
            name = "id_generator",
            sequenceName = "id_generator",
            allocationSize = 1,
            initialValue = 200
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "id_generator"
    )
    private int id;

    @NotEmpty(
            message = "user name cannot be empty"
    )
    private String userName;

    @NotEmpty(
            message = "Email cannot be empty"
    )
    @Email
    private String email;

    @NotBlank(
            message = "pass cannot be blank"
    )
    private String password;

    public void setUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            throw new CustomValidationException("user name cannot be empty");
        }
        this.userName = userName;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new CustomValidationException("email cannot be empty");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new CustomValidationException("password cannot be empty");
        }
        this.password = password;
    }

}
