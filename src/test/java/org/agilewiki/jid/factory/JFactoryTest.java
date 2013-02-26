package org.agilewiki.jid.factory;

import junit.framework.TestCase;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jaosgi.JABCNoOsgiImpl;
import org.agilewiki.jaosgi.JABundleContext;

/**
 * Test code.
 */
public class JFactoryTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = JABCNoOsgiImpl.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            factoryLocator.defineActorType("Foo", Foo.class);
            Foo foo = (Foo) factoryLocator.newActor("Foo");
            foo.hi();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
