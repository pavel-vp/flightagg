package com.someplatform.flightagg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SortedObject {
    private FieldSpec fieldSpec;
    private boolean asc;

    public SortedObject(FieldSpec fieldSpec, boolean asc) {
        this.fieldSpec = fieldSpec;
        this.asc = asc;
    }
}
