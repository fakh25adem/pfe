package com.example.Client.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUp {
    private Integer id_personne;
  @NotNull(message = "Le cin ne peut pas être vide")
    @Min(value = 10000000, message = "Le cin doit être un nombre entier de 8 chiffres ")
    @Max(value = 99999999, message = "Le cin doit être un nombre entier de 8 chiffres")
    private Integer cin;

    @Size(max=15,min=1,message = "La taille  du Nom doit etre compris entre 1 et 15 caractère ")

    private String nom;

    @Size(max=15,min=1,message = "La taille du prénom doit etre compris entre 1 et 15 caractère")
    private String prenom;
  @NotBlank(message = "Saisir un email")
    @Email(message = "doit être une adresse électronique")
    private String email;

    @Size(min=7,message = "La taille du mot de passe doit dépasser 6 caractère ")
    private String mdp;

    @Size(max=13,min=8,message = "La taille du  numéro du télépohone doit etre compris entre 8 et 13 caractère ")
    private String tel ;
    @NotBlank(message = "Selectionner un  role")
    private String role;
}
