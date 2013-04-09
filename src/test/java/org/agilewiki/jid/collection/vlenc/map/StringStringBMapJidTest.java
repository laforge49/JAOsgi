package org.agilewiki.jid.collection.vlenc.map;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.collection.vlenc.map.BMap;
import org.agilewiki.incdes.impl.collection.vlenc.map.MapEntryImpl;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class StringStringBMapJidTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            BMap<String, PAStringImpl> m = (BMap) factoryLocator.
                    newJid(PAFactories.STRING_STRING_BMAP_JID_TYPE);
            assertEquals(0, m.size());
            assertTrue(m.kMake("1"));
            assertFalse(m.kMake("1"));
            assertEquals(1, m.size());
            MapEntryImpl<String, PAStringImpl> me = m.iGet(0);
            assertEquals("1", me.getKey());
            PAStringImpl v = m.kGet("1");
            assertEquals(v, me.getValue());
            assertEquals(me, m.getCeiling("0"));
            assertEquals(me, m.getCeiling("1"));
            assertNull(m.getCeiling("2"));
            assertEquals(me, m.getHigher("0"));
            assertNull(m.getHigher("1"));
            m.empty();
            assertEquals(0, m.size());
            assertTrue(m.kMake("1"));
            assertEquals(1, m.size());
            me = m.iGet(0);
            assertEquals("1", me.getKey());
            m.iRemove(0);
            assertEquals(0, m.size());
            assertTrue(m.kMake("1"));
            assertEquals(1, m.size());
            me = m.iGet(0);
            assertEquals("1", me.getKey());
            assertFalse(m.kRemove("0"));
            assertTrue(m.kRemove("1"));
            assertEquals(0, m.size());
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
