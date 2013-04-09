package org.agilewiki.jid.scalar.vlens.actor;

import junit.framework.TestCase;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.incdes.impl.scalar.vlens.UnionImpl;

public class UnionJidTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            UnionJidFactory siuf =
                    new UnionJidFactory("siUnion", JidFactories.STRING_JID_TYPE, "siUnion");
            factoryLocator.registerJidFactory(siuf);
            UnionImpl siu1 = (UnionImpl) factoryLocator.newJid("siUnion");
            assertNull(siu1.getValue());
            UnionImpl siu2 = (UnionImpl) siu1.copyJID(factoryLocator.getMailbox());
            assertNull(siu2.getValue());
            siu2.setValue(JidFactories.STRING_JID_TYPE);
            PAStringImpl sj2 = (PAStringImpl) siu2.getValue();
            assertNotNull(sj2);
            UnionImpl siu3 = (UnionImpl) siu2.copyJID(factoryLocator.getMailbox());
            PAStringImpl sj3 = (PAStringImpl) siu3.getValue();
            assertNotNull(sj3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
