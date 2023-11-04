package com.canalplus.subscriber.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class SubscriberDto {
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
