package org.agilewiki.jid.scalar.vlens.actor;

import junit.framework.TestCase;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.vlens.StringJid;

public class UnionJidTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            UnionJidFactory siuf =
                    new UnionJidFactory("siUnion", JidFactories.STRING_JID_TYPE, "siUnion");
            factoryLocator.registerJidFactory(siuf);
            UnionJid siu1 = (UnionJid) factoryLocator.newJid("siUnion");
            assertNull(siu1.getValue());
            UnionJid siu2 = (UnionJid) siu1.copyJID(factoryLocator.getMailbox());
            assertNull(siu2.getValue());
            siu2.setValue(JidFactories.STRING_JID_TYPE);
            StringJid sj2 = (StringJid) siu2.getValue();
            assertNotNull(sj2);
            UnionJid siu3 = (UnionJid) siu2.copyJID(factoryLocator.getMailbox());
            StringJid sj3 = (StringJid) siu3.getValue();
            assertNotNull(sj3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
