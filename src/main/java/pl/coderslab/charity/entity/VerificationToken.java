package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    private String token = UUID.randomUUID().toString();

}
