package dev.auth;

public  class JWTConcreteBuilder implements JWTBuilder{
    public JWTConfiguration configuration;
    @Override
    public  void setHeader(String header) {
        this.configuration.setHeader(header);
    }

    @Override
    public void setPayload(String payload) {
        this.configuration.setPayload(payload);
    }

    @Override
    public void setSignature(String signature) {
        this.configuration.setSignature(signature);
    }
}
