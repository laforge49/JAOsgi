package org.agilewiki.jid.collection.vlenc.map;

import junit.framework.TestCase;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

public class StringStringBMapJidTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            BMapJid<String, StringJid> m = (BMapJid) factoryLocator.
                    newJid(JidFactories.STRING_STRING_BMAP_JID_TYPE);
            assertEquals(0, m.size());
            assertTrue(m.kMake("1"));
            assertFalse(m.kMake("1"));
            assertEquals(1, m.size());
            MapEntry<String, StringJid> me = m.iGet(0);
            assertEquals("1", me.getKey());
            StringJid v = m.kGet("1");
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
