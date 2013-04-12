package org.agilewiki.incdes;

import junit.framework.TestCase;
//import org.agilewiki.jactor.old.Actor;


public class StringStringMapTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAMap<String, PAString> m = (PAMap) factoryLocator.newJid(IncDesFactories.STRING_PASTRING_MAP);
            /*
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
            */
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
