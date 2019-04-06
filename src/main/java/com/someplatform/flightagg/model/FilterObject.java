package com.someplatform.flightagg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilterObject {
    private FieldSpec fieldSpec;
    private EquisionType equisionType;
    private String filterValue;

    public FilterObject(FieldSpec fieldSpec, EquisionType equisionType, String filterValue) {
        this.fieldSpec = fieldSpec;
        this.equisionType = equisionType;
        this.filterValue = filterValue;
    }
}
