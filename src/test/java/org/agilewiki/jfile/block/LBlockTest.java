package org.agilewiki.jfile.block;

import junit.framework.TestCase;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;
import org.agilewiki.jid.scalar.vlens.actor.RootJidFactory;

public class LBlockTest extends TestCase {
    public void test()
            throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);

        RootJid rj = RootJidFactory.create(factoryLocator, null, null);
        LBlock lb1 = new LBlock();
        lb1.setRootJid(rj);
        byte[] bs = lb1.serialize();

        int hl = lb1.headerLength();
        int rjl = rj.getSerializedLength();
        assertEquals(bs.length, hl + rjl);

        byte[] h = new byte[hl];
        System.arraycopy(bs, 0, h, 0, hl);
        byte[] sd = new byte[rjl];
        System.arraycopy(bs, hl, sd, 0, rjl);

        LBlock lb2 = new LBlock();
        int rjl2 = lb2.setHeaderBytes(h);
        lb2.setRootJidBytes(sd);
        RootJid rj2 = lb2.getRootJid(factoryLocator, null, null);

        jaBundleContext.stop(0);
    }
}
