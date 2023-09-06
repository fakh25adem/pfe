package com.example.Client.Controller;



import com.example.Client.Config.JwtUtils;
import com.example.Client.Dto.JwtResponse;
import com.example.Client.Dto.Login;


import com.example.Client.Dto.SignUp;
import com.example.Client.Model.Alarme;
import com.example.Client.Model.Personne;
import com.example.Client.Repository.AlarmeRepository;
import com.example.Client.Repository.PersonneRepository;
import com.example.Client.Service.ServicePersonne;
import jakarta.validation.Valid;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/personne")
@AllArgsConstructor

public class AuthController {

 private  AuthenticationManager authenticationManager;

 private JwtUtils jwtUtils;
 private PersonneRepository personneRepository;
 private AlarmeRepository alarmeRepository;
 private ServicePersonne servicePersonne;



    @PostMapping("/creer")
    public ResponseEntity<Object> Create(@RequestBody @Valid SignUp signUp, BindingResult result) {
      Personne personne= personneRepository.findByEmail(signUp.getEmail());
      if(personne!=null) {
          return ResponseEntity.badRequest().body("Email exist");
      }
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .sorted()
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);

        }
        System.out.println(signUp.getRole().equals("user"));
       servicePersonne.creer(( servicePersonne.ajouter(signUp)));
        return ResponseEntity.ok( "Inscription avec succès ");
    }
    @PostMapping ("/up")
    public ResponseEntity<?> authenticateUser(@RequestBody  Login loginRequest) {

        try {

            Authentication   authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                    loginRequest.getMdp()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            return ResponseEntity.ok(new JwtResponse(jwt));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Email ou mot de passe");
        }

    }
    @GetMapping("/get")
    public List<Alarme> get(){
    return alarmeRepository.findAll();

    }

};

