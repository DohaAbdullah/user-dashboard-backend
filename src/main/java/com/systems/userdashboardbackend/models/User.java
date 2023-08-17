package com.systems.userdashboardbackend.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "Last_name")
    private String last_name;
    @Column(name = "city")
    private String city;
    @Column(name = "email")
    private String email;

}
