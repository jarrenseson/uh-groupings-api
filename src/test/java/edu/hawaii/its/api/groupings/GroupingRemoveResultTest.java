package edu.hawaii.its.api.groupings;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import edu.hawaii.its.api.util.JsonUtil;
import edu.hawaii.its.api.wrapper.RemoveMemberResult;

import edu.internet2.middleware.grouperClient.ws.beans.WsDeleteMemberResult;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupingRemoveResultTest {
    private static Properties properties;

    @BeforeAll
    public static void beforeAll() throws Exception {
        Path path = Paths.get("src/test/resources");
        Path file = path.resolve("grouper.test.properties");
        properties = new Properties();
        properties.load(new FileInputStream(file.toFile()));
    }

    @Test
    public void test() {
        String json = propertyValue("ws.delete.member.results.success");
        WsDeleteMemberResults wsDeleteMemberResults = JsonUtil.asObject(json, WsDeleteMemberResults.class);
        assertNotNull(wsDeleteMemberResults);
        assertTrue(wsDeleteMemberResults.getResults().length > 0);
        WsDeleteMemberResult wsDeleteMemberResult = wsDeleteMemberResults.getResults()[0];
        assertNotNull(wsDeleteMemberResult);
        GroupingRemoveResult groupingRemoveResult =
                new GroupingRemoveResult(new RemoveMemberResult(wsDeleteMemberResult, "group-path"));

        assertNotNull(groupingRemoveResult);
        assertEquals("SUCCESS_WASNT_IMMEDIATE", groupingRemoveResult.getResultCode());
        assertEquals(getTestUsernames().get(0), groupingRemoveResult.getUid());
        assertEquals(getTestNumbers().get(0), groupingRemoveResult.getUhUuid());
        assertEquals(getTestNames().get(0), groupingRemoveResult.getName());
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
