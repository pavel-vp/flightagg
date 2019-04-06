package com.someplatform.flightagg.model;

public enum FieldSpec {
    ID("id"), DEP("departure"), ARR("arrival"), DEPTIME("departureTime"), ARRTIME("arrivalTime");

    private final String description;

    FieldSpec(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static FieldSpec findFieldSpecByFieldName(String fieldName) {
        for (FieldSpec spec : FieldSpec.values()) {
            if (fieldName.equals(spec.getDescription())) {
                return spec;
            }
        }
        return null;
    }


}
