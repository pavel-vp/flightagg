package com.someplatform.flightagg;

import com.someplatform.flightagg.model.EquisionType;
import com.someplatform.flightagg.model.FieldSpec;
import com.someplatform.flightagg.model.FilterObject;
import com.someplatform.flightagg.model.SortedObject;
import com.someplatform.flightagg.service.FieldExtractService;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightaggParamParsingTest {

    private FieldExtractService fieldExtractService = new FieldExtractService();

    @Test
    public void extract_order_test() {
        String orderStr = "id;desc";
        SortedObject sortedObject = fieldExtractService.buildSortedObject(orderStr);
        Assert.assertTrue(sortedObject != null && sortedObject.getFieldSpec() == FieldSpec.ID && !sortedObject.isAsc());
    }

    @Test
    public void extract_filter_test() {
        Map<String, String[]> paramMap = new HashMap<>();
        paramMap.put("f_departure", new String[] {"like;Moscow"});
        List<FilterObject> filterObjects = fieldExtractService.buildFilters(paramMap);
        Assert.assertTrue(filterObjects != null &&
                filterObjects.size() == 1 &&
                filterObjects.get(0).getFieldSpec() == FieldSpec.DEP &&
                filterObjects.get(0).getEquisionType() == EquisionType.LIKE &&
                "Moscow".equals(filterObjects.get(0).getFilterValue()));
    }
}
