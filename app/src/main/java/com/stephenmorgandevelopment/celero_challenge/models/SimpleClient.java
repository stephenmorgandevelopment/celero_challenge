package com.stephenmorgandevelopment.celero_challenge.models;

public class SimpleClient {
    private long identifier;
    private String name;
    private String reason;

    public SimpleClient(long identifier, String name, String reason) {
        this.identifier = identifier;
        this.name = name;
        this.reason = reason;
    }

    public long getIdentifier() {return identifier;}
    public String getName() {return name;}
    public String getReason() {return reason;}
}
