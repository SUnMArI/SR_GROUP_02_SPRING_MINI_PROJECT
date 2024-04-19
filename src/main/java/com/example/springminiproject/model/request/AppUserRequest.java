package com.example.springminiproject.model.request;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @NotNull
    @Size(min = 6)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "confirm password must be at least 8 characters long and include both letters and numbers and special character")
    private String password;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "confirm password must be at least 8 characters long and include both letters and numbers and special character")
    private String confirmPassword;

    @NotBlank
    @NotNull
    private String profileImage;
}
