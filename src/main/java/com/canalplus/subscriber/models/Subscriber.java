package com.canalplus.subscriber.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String fname;
    @NotNull
    private String lname;
    @NotNull
    @Email(regexp=".*@.*\\..*", message = "Entrer un mail valide")
    private String mail;
    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;
    private boolean isActive;
}
