package org.agilewiki.jid.collection.vlenc.map;

import junit.framework.TestCase;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.impl.collection.vlenc.map.MapEntryImpl;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class StringStringMapJidTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = IncDesFactories.createFactoryLocator(1);
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Actor m = factoryLocator.newJid(IncDesFactories.STRING_STRING_MAP_JID_TYPE);
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
