package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Institution {

    @Id
    private Long id;

    private String name;
    private String description;
}
