package org.agilewiki.jid.collection.vlenc.map;

import junit.framework.TestCase;
import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jaosgi.JABundleContext;
import org.agilewiki.jaosgi.JAFactoryLocator;
import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

public class StringStringMapJidTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Actor m = factoryLocator.newActor(JidFactories.STRING_STRING_MAP_JID_TYPE);
            assertNull(new KGet<String, StringJid>("a").send(future, m));
            assertTrue(new KMake<String, StringJid>("b").send(future, m));
            assertNull(new KGet<String, StringJid>("a").send(future, m));
            Actor value = new KGet<String, StringJid>("b").send(future, m);
            assertNotNull(value);
            assertTrue(value instanceof StringJid);
            value = new GetHigher<String, StringJid>("a").send(future, m);
            assertTrue(value instanceof MapEntry);
            assertNull(new GetHigher<String, StringJid>("b").send(future, m));
            value = new GetCeiling<String, StringJid>("b").send(future, m);
            assertTrue(value instanceof MapEntry);
            assertNull(new GetCeiling<String, StringJid>("c").send(future, m));
            assertNull(new KGet<String, StringJid>("c").send(future, m));
            assertTrue(new KRemove<String, StringJid>("b").send(future, m));
            assertFalse(new KRemove<String, StringJid>("b").send(future, m));
            assertNull(new KGet<String, StringJid>("b").send(future, m));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}