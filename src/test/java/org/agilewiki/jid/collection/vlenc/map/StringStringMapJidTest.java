package org.agilewiki.jid.collection.vlenc.map;

import junit.framework.TestCase;
import org.agilewiki.incdes.impl.collection.vlenc.map.MapEntryImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;

public class StringStringMapJidTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Actor m = factoryLocator.newJid(JidFactories.STRING_STRING_MAP_JID_TYPE);
            assertNull(new KGet<String, PAStringImpl>("a").send(future, m));
            assertTrue(new KMake<String, PAStringImpl>("b").send(future, m));
            assertNull(new KGet<String, PAStringImpl>("a").send(future, m));
            Actor value = new KGet<String, PAStringImpl>("b").send(future, m);
            assertNotNull(value);
            assertTrue(value instanceof PAStringImpl);
            value = new GetHigher<String, PAStringImpl>("a").send(future, m);
            assertTrue(value instanceof MapEntryImpl);
            assertNull(new GetHigher<String, PAStringImpl>("b").send(future, m));
            value = new GetCeiling<String, PAStringImpl>("b").send(future, m);
            assertTrue(value instanceof MapEntryImpl);
            assertNull(new GetCeiling<String, PAStringImpl>("c").send(future, m));
            assertNull(new KGet<String, PAStringImpl>("c").send(future, m));
            assertTrue(new KRemove<String, PAStringImpl>("b").send(future, m));
            assertFalse(new KRemove<String, PAStringImpl>("b").send(future, m));
            assertNull(new KGet<String, PAStringImpl>("b").send(future, m));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
