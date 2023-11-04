package com.canalplus.subscriber.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;



@Data
public class SubscriberDto {
    private Long id;
    @NotNull
    @NotEmpty(message = "The full name is required.")
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
