package org.agilewiki.jid.scalar.vlens.actor;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.incdes.impl.scalar.vlens.UnionImpl;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class UnionJidTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            UnionJidFactory siuf =
                    new UnionJidFactory("siUnion", PAFactories.STRING_JID_TYPE, "siUnion");
            factoryLocator.registerJidFactory(siuf);
            UnionImpl siu1 = (UnionImpl) factoryLocator.newJid("siUnion");
            assertNull(siu1.getValue());
            UnionImpl siu2 = (UnionImpl) siu1.copyJID(factoryLocator.getMailbox());
            assertNull(siu2.getValue());
            siu2.setValue(PAFactories.STRING_JID_TYPE);
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
