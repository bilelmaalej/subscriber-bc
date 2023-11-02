package com.canalplus.subscriber.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberCriteria {
    private Long id;
    private String fname;
    private String lname;
    private String mail;
    private String phone;
}
