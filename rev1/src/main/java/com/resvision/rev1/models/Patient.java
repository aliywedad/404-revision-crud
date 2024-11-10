package com.resvision.rev1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Patient {
    @Id
    @GeneratedValue
    long id;
    String name;
    int age;
    String gender;
    String address;
    String phone;
    String email;
}
