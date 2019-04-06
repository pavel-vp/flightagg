package com.someplatform.flightagg.service;

import com.someplatform.flightagg.model.EquisionType;
import com.someplatform.flightagg.model.FieldSpec;
import com.someplatform.flightagg.model.FilterObject;
import com.someplatform.flightagg.model.SortedObject;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FieldExtractService {

    public List<FilterObject> buildFilters(Map<String, String[]> parameterMap) {
        List<FilterObject> result = new ArrayList<>();
        for (Map.Entry<String, String[]> ent : parameterMap.entrySet()) {
            String fieldName = ent.getKey();
            String val = ent.getValue()[0];
            if (! fieldName.startsWith("f_") || val == null)
                continue;
            FieldSpec fieldSpec = FieldSpec.findFieldSpecByFieldName(fieldName.substring(2));

            String[] fieldFilterConds = val.split(";");
            if (fieldFilterConds.length < 2)
                continue;
            EquisionType eqType = EquisionType.findByDesc(fieldFilterConds[0]);

            FilterObject filterObject = new FilterObject(fieldSpec, eqType, fieldFilterConds[fieldFilterConds.length - 1]);
            result.add(filterObject);

        }
        return result;
    }

    public SortedObject buildSortedObject(String sorted) {
        if (sorted == null)
            return null;
        String[] arr = sorted.split(";");
        if (arr.length < 2)
            return null;
        FieldSpec fieldSpec = FieldSpec.findFieldSpecByFieldName(arr[0]);
        if (fieldSpec == null)
            return null;
        return new SortedObject(fieldSpec, "asc".equals(arr[1]));
    }
}
