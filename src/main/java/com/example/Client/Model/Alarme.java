package com.example.Client.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@Table(name = "alarme")
@Getter
@Setter
@AllArgsConstructor
@DynamicUpdate(value=false)
public class Alarme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_alarme;
    @Column(name = "Status")

    private String status_alarm;
    @Column(name = "dateAlarm")
    private LocalDateTime datealarm;

}
