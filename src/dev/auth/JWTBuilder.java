package dev.auth;

public interface JWTBuilder {
    public  void setHeader(String header);
    public  void setPayload(String payload);
    public  void setSignature(String signature);


}
