package org.agilewiki.incdes;

import junit.framework.TestCase;


public class StringPAStringMapTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            PAMap<String, PAString> m = (PAMap) factoryLocator.newJid(IncDesFactories.STRING_PASTRING_MAP);
            assertNull(m.kGetReq("a").call());
            assertTrue(m.kMakeReq("b").call());
            assertNull(m.kGetReq("a").call());
            PAString value = m.kGetReq("b").call();
            assertNotNull(value);
            MapEntry<String, PAString> entry = m.getHigherReq("a").call();
            assertNotNull(entry);
            assertNull(m.getHigherReq("b").call());
            entry = m.getCeilingReq("b").call();
            assertNotNull(entry);
            assertNull(m.getCeilingReq("c").call());
            assertNull(m.kGetReq("c").call());
            assertTrue(m.kRemoveReq("b").call());
            assertFalse(m.kRemoveReq("b").call());
            assertNull(m.kGetReq("b").call());
        } finally {
            factoryLocator.close();
        }
    }
}
