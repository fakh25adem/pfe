package com.example.Client.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "camera")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Camera {
    @Id
    @GeneratedValue
    private Integer id_camera;
    @Column(name = "adrcamera")
    private String adrcamera;

    @OneToOne(mappedBy = "camera",cascade = CascadeType.ALL)
    @JoinColumn(unique=true,name = "cam_id")
    private Porte p;
}
