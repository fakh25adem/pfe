package com.example.Client.Service;

import com.example.Client.Dto.SignUp;
import com.example.Client.Model.Personne;
import com.example.Client.Model.Role;
import com.example.Client.Repository.PersonneRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ServicePersonneImpl implements ServicePersonne {
    private final PersonneRepository personneRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<Object> creer(Personne p) {
        return ResponseEntity.ok(personneRepository.save(p));
    }

    @Override
    public Personne findBycin(Integer cin) {
        return personneRepository.findBycin(cin);
    }

    @Override
    public Personne ajouter(SignUp signUp) {
        Role role = new Role();
        if (Objects.equals(signUp.getRole(), "user")){
            role.setId(2);
            role.setRole("user");
            Personne p = new Personne();
            p.setCin(signUp.getCin());
            p.setNom(signUp.getNom());
            p.setPrenom(signUp.getPrenom());
            p.setEmail(signUp.getEmail());
            p.setTel(signUp.getTel());
            p.setRole(role);
            p.setMdp(passwordEncoder.encode(signUp.getMdp()));
            return p;
        }else{
            role.setId(1);
            role.setRole("admin");
            Personne p = new Personne();
            p.setCin(signUp.getCin());
            p.setNom(signUp.getNom());
            p.setPrenom(signUp.getPrenom());
            p.setEmail(signUp.getEmail());
            p.setTel(signUp.getTel());
            p.setRole(role);
            p.setMdp(passwordEncoder.encode(signUp.getMdp()));

            return p;
        }

    }


}



