package com.someplatform.flightagg.model;

public enum EquisionType {
    GTE("gte"), LTE("lte"), LIKE("like");

    private final String description;
    EquisionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static EquisionType findByDesc(String description) {
        for (EquisionType eq : EquisionType.values()) {
            if (eq.getDescription().equals(description)) {
                return eq;
            }
        }
        return null;
    }

}
