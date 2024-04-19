package com.example.springminiproject.controller;

import com.example.springminiproject.exception.NotFoundException;
import com.example.springminiproject.jwt.JwtService;
import com.example.springminiproject.model.request.AuthRequest;
import com.example.springminiproject.model.response.AuthResponse;
import com.example.springminiproject.model.request.ForgetPasswordRequest;
import com.example.springminiproject.service.AuthService;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private void authenticate(String username, String password) throws Exception {
        try {
            UserDetails userApp = authService.loadUserByUsername(username);
            if (userApp == null) {
                throw new NotFoundException("Wrong Email");
            }
            if (!passwordEncoder.matches(password, userApp.getPassword())) {
                throw new NotFoundException("Wrong Password");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    //  Login
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        authenticate(authRequest.getEmail(), authRequest.getPassword());
        final UserDetails userDetails = authService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);
        AuthResponse authResponse = new AuthResponse(token);
        return ResponseEntity.ok(authResponse);
    }

    @PutMapping("/verify")
    public ResponseEntity<?> verifyCode( @Positive Integer otpCode){
        authService.verifyOtpCode(otpCode);
        return ResponseEntity.ok("Account was verified successful");
    }

    @PutMapping("/forget")
    public ResponseEntity<?> forgetPassword(String email, @RequestBody ForgetPasswordRequest forgetPasswordRequest){
        authService.forgetPassword(email,forgetPasswordRequest);
        return ResponseEntity.ok("Password was changed successful");
    }


    @GetMapping("/resend")
    public ResponseEntity<?> resendCode(String email){
        authService.resendCode(email);
        return ResponseEntity.ok("Code Already resend");
    }
}

