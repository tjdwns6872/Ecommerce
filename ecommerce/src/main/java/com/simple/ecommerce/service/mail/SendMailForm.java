package com.simple.ecommerce.service.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SendMailForm {
    private String from;
    private String to;
    private String subject;
    private String text;
}