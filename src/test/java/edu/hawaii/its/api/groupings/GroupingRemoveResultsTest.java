package edu.hawaii.its.api.groupings;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import edu.hawaii.its.api.util.JsonUtil;
import edu.hawaii.its.api.wrapper.RemoveMemberResult;
import edu.hawaii.its.api.wrapper.RemoveMembersResults;

import edu.internet2.middleware.grouperClient.ws.beans.WsDeleteMemberResults;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GroupingRemoveResultsTest {
    private static Properties properties;

    @BeforeAll
    public static void beforeAll() throws Exception {
        Path path = Paths.get("src/test/resources");
        Path file = path.resolve("grouper.test.properties");
        properties = new Properties();
        properties.load(new FileInputStream(file.toFile()));
    }

    @Test
    public void constructor() {
        String json = propertyValue("ws.delete.member.results.success");
        WsDeleteMemberResults wsDeleteMemberResults = JsonUtil.asObject(json, WsDeleteMemberResults.class);
        assertNotNull(wsDeleteMemberResults);
        RemoveMembersResults removeMembersResults = new RemoveMembersResults(wsDeleteMemberResults);
        assertNotNull(removeMembersResults);
        GroupingRemoveResults groupingRemoveResults = new GroupingRemoveResults(removeMembersResults);
        assertNotNull(groupingRemoveResults);
    }

    @Test
    public void test() {
        String json = propertyValue("ws.delete.member.results.success");
        WsDeleteMemberResults wsDeleteMemberResults = JsonUtil.asObject(json, WsDeleteMemberResults.class);
        RemoveMembersResults removeMembersResults = new RemoveMembersResults(wsDeleteMemberResults);
        GroupingRemoveResults groupingRemoveResults = new GroupingRemoveResults(removeMembersResults);
        assertEquals("SUCCESS", groupingRemoveResults.getResultCode());

        json = propertyValue("ws.delete.member.results.failure");
        wsDeleteMemberResults = JsonUtil.asObject(json, WsDeleteMemberResults.class);
        removeMembersResults = new RemoveMembersResults(wsDeleteMemberResults);
        groupingRemoveResults = new GroupingRemoveResults(removeMembersResults);
        assertEquals("FAILURE", groupingRemoveResults.getResultCode());
    }

    @Test
    public void add() {
        String json = propertyValue("ws.delete.member.results.success");
        WsDeleteMemberResults wsDeleteMemberResults = JsonUtil.asObject(json, WsDeleteMemberResults.class);
        RemoveMembersResults removeMembersResults = new RemoveMembersResults(wsDeleteMemberResults);
        GroupingRemoveResults groupingRemoveResults = new GroupingRemoveResults(removeMembersResults);
        List<GroupingRemoveResult> results = groupingRemoveResults.getResults();
        assertNotNull(results);

        RemoveMemberResult removeMemberResult =
                new RemoveMemberResult(wsDeleteMemberResults.getResults()[0], "group-path");
        GroupingRemoveResult groupingRemoveResult = new GroupingRemoveResult(removeMemberResult);
        assertEquals(5, groupingRemoveResults.getResults().size());
        groupingRemoveResults.add(groupingRemoveResult);
        assertEquals(6, groupingRemoveResults.getResults().size());

        json = propertyValue("ws.delete.member.results.success");
        wsDeleteMemberResults = JsonUtil.asObject(json, WsDeleteMemberResults.class);
        removeMembersResults = new RemoveMembersResults(wsDeleteMemberResults);
        GroupingRemoveResults resultsToAdd = new GroupingRemoveResults(removeMembersResults);
        groupingRemoveResults.add(resultsToAdd);
        assertNotNull(groupingRemoveResults);
        assertEquals(11, groupingRemoveResults.getResults().size());
    }

    public List<String> getTestUsernames() {
        String[] array = { "testiwta", "testiwtb", "testiwtc", "testiwtd", "testiwte" };
        return new ArrayList<>(Arrays.asList(array));
    }

    public List<String> getTestNumbers() {
        String[] array = { "99997010", "99997027", "99997033", "99997043", "99997056" };
        return new ArrayList<>(Arrays.asList(array));
    }

    public List<String> getTestNames() {
        String[] array = { "Testf-iwt-a TestIAM-staff", "Testf-iwt-b TestIAM-staff", "Testf-iwt-c TestIAM-staff",
                "Testf-iwt-d TestIAM-faculty", "Testf-iwt-e TestIAM-student" };
        return new ArrayList<>(Arrays.asList(array));
    }

    public List<String> getTestFirstNames() {
        String[] array = { "Testf-iwt-a", "Testf-iwt-b", "Testf-iwt-c", "Testf-iwt-d", "Testf-iwt-e" };
        return new ArrayList<>(Arrays.asList(array));
    }

    public List<String> getTestLastNames() {
        String[] array = { "TestIAM-staff", "TestIAM-staff", "TestIAM-staff", "TestIAM-faculty", "TestIAM-student" };
        return new ArrayList<>(Arrays.asList(array));
    }

    private String propertyValue(String key) {
        return properties.getProperty(key);
    }
}
