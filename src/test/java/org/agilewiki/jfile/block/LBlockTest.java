package org.agilewiki.jfile.block;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.vlens.RootImpl;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class LBlockTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);

        RootImpl rj = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
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
        RootImpl rj2 = lb2.getRootJid(factoryLocator, null, null);

        jaBundleContext.stop(0);
    }
}
