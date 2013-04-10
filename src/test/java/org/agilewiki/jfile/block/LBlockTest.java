package org.agilewiki.jfile.block;

import junit.framework.TestCase;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.Root;

public class LBlockTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            Root rj = (Root) factoryLocator.newJid(IncDesFactories.ROOT_JID_TYPE);
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
            Root rj2 = lb2.getRootJid(factoryLocator, null, null);
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
