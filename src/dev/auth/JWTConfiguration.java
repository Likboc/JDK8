package dev.auth;

import lombok.Data;


@Data
public class JWTConfiguration {
    public String header;
    public String payload;
    public String signature;
}