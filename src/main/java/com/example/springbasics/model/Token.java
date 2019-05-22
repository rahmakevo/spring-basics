package com.example.springbasics.model;

import lombok.Data;

@Data
public class Token {
    String token;
    Long expTime;
}
