package org.agilewiki.jid.collection.vlenc.map;

import junit.framework.TestCase;
import org.agilewiki.incdes.impl.collection.vlenc.map.BMap;
import org.agilewiki.incdes.impl.collection.vlenc.map.MapEntryImpl;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;

public class LongStringBMapJidTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            BMap<Long, PAStringImpl> m = (BMap) factoryLocator.
                    newJid(JidFactories.LONG_STRING_BMAP_JID_TYPE);
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
