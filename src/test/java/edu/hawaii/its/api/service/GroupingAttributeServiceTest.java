package edu.hawaii.its.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.hawaii.its.api.configuration.SpringBootWebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.hawaii.its.api.type.GroupingsServiceResult;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { SpringBootWebApplication.class })
class GroupingAttributeServiceTest {

    @Autowired
    private GroupingAttributeService groupingAttributeService;

    @Test
    public void construction() {
        assertNotNull(groupingAttributeService);
    }

    @Test
    public void makeGroupingsServiceResultTest() {
        String resultCode = "resultCode";
        String action = "action";
        GroupingsServiceResult groupingsServiceResult = groupingAttributeService.makeGroupingsServiceResult(resultCode, action);
        assertNotNull(groupingsServiceResult);
        assertEquals(resultCode, groupingsServiceResult.getResultCode());
        assertEquals(action, groupingsServiceResult.getAction());
    }
}
