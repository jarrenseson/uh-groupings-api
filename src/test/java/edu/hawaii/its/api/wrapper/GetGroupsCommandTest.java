package edu.hawaii.its.api.wrapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetGroupsCommandTest {
    @Test
    public void constructor() {
        GetGroupsCommand getGroupsCommand = new GetGroupsCommand();
        assertNotNull(getGroupsCommand);

    }
    @Test
    public void builders() {
        GetGroupsCommand getGroupsCommand = new GetGroupsCommand();
        assertNotNull(getGroupsCommand.addUhIdentifier(""));
        assertNotNull(getGroupsCommand.addUhIdentifier("11111111"));
        assertNotNull(getGroupsCommand.query(""));
    }
}
