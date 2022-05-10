package com.sunshine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    @Size(min = 5, message = "Must be 5 characters long")
    private String password;
    @Column
    private boolean isActive =false;
    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.USER;




}
