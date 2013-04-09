package org.agilewiki.jid.collection.vlenc.map;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.collection.vlenc.map.BMap;
import org.agilewiki.incdes.impl.collection.vlenc.map.MapEntryImpl;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class LongStringBMapJidTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            BMap<Long, PAStringImpl> m = (BMap) factoryLocator.
                    newJid(PAFactories.LONG_STRING_BMAP_JID_TYPE);
            assertEquals(0, m.size());
            assertTrue(m.kMake(1L));
            assertFalse(m.kMake(1L));
            assertEquals(1, m.size());
            MapEntryImpl<Long, PAStringImpl> me = m.iGet(0);
            assertEquals((Long) 1L, me.getKey());
            PAStringImpl v = m.kGet(1L);
            assertEquals(v, me.getValue());
            assertEquals(me, m.getCeiling(0L));
            assertEquals(me, m.getCeiling(1L));
            assertNull(m.getCeiling(2L));
            assertEquals(me, m.getHigher(0L));
            assertNull(m.getHigher(1L));
            m.empty();
            assertEquals(0, m.size());
            assertTrue(m.kMake(1L));
            assertEquals(1, m.size());
            me = m.iGet(0);
            assertEquals((Long) 1L, me.getKey());
            m.iRemove(0);
            assertEquals(0, m.size());
            assertTrue(m.kMake(1L));
            assertEquals(1, m.size());
            me = m.iGet(0);
            assertEquals((Long) 1L, me.getKey());
            assertFalse(m.kRemove(0L));
            assertTrue(m.kRemove(1L));
            assertEquals(0, m.size());
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
