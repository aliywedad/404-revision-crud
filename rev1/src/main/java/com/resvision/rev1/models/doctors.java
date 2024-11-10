package com.resvision.rev1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class doctors {
    @Id
    long id;
    String name;
    String email;
    String address;
    String phone;
    String gender;
    String specialite;
}
