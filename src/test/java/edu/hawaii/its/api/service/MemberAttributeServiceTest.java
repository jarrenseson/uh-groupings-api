package edu.hawaii.its.api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.hawaii.its.api.configuration.SpringBootWebApplication;
import edu.hawaii.its.api.util.PropertyLocator;

import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResult;
import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResults;
import edu.internet2.middleware.grouperClient.ws.beans.WsResultMeta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = { SpringBootWebApplication.class })
public class MemberAttributeServiceTest {

    private PropertyLocator propertyLocator;

    @MockBean
    private GrouperApiService grouperApiService;

    @Autowired
    private MemberAttributeService memberAttributeService;

    final String groupAdminPath = "uh-settings:groupingAdmins";
    final String groupOwnerPath = "uh-settings:groupingOwners";
    final String username = "uuu";
    final String uid = "123";

    @BeforeEach
    public void beforeEach() throws Exception {
        propertyLocator = new PropertyLocator("src/test/resources", "grouper.test.properties");
    }

    @Test
    public void construction() {
        assertNotNull(memberAttributeService);
    }


    @Test
    public void isUhUuid() {
        assertTrue(memberAttributeService.isUhUuid("111111"));
        assertFalse(memberAttributeService.isUhUuid("111-111"));
        assertFalse(memberAttributeService.isUhUuid("iamtst01"));
        assertFalse(memberAttributeService.isUhUuid(null));
    }

    /**
     * Helper - getMemberAttributesSubjectFound, getMemberAttributesSubjectNotFound, getMemberAttributesNotAdminNotOwner, getMemberAttributesAdminButNotOwner, getMemberAttributesOwnerButNotAdmin
     */
    private WsHasMemberResults makeWsHasMemberResults(final String resultCode) {
        return new WsHasMemberResults() {
            @Override
            public WsHasMemberResult[] getResults() {
                WsHasMemberResult a = new WsHasMemberResult() {
                    @Override
                    public WsResultMeta getResultMetadata() {
                        WsResultMeta b = new WsResultMeta() {
                            @Override
                            public String getResultCode() {
                                return resultCode;
                            }
                        };
                        return b;
                    }
                };
                WsHasMemberResult[] results = { a };
                return results;
            }
        };
    }
}
