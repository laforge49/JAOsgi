package org.agilewiki.incdes;

import junit.framework.TestCase;

public class ListTest extends TestCase {
    public void test() throws Exception {

        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PAList<PAString> l0 = (PAList) factoryLocator.newJid(IncDesFactories.PASTRING_LIST);
            int l0sl = l0.getSerializedLength();
            assertEquals(8, l0sl);
            PAList<PAString> l1 = (PAList) l0.copyReq(null).call();
            int l1sl = l1.getSerializedLength();
            assertEquals(8, l1sl);
            l1.iAddReq(0).call();
            l1sl = l1.getSerializedLength();
            assertEquals(12, l1sl);
            PAList<PAString> l2 = (PAList) l1.copyReq(null).call();
            int l2sl = l2.getSerializedLength();
            assertEquals(12, l2sl);
            PAString s0 = IncDesFactories.createPAString(factoryLocator, null, null);
            s0.setStringReq("Hi").call();
            int s0sl = s0.getSerializedLength();
            assertEquals(8, s0sl);
            byte[] s0bs = s0.getSerializedBytes();
            assertEquals(8, s0bs.length);
            l2.iAddReq(-1, s0bs).call();
            l2sl = l2.getSerializedLength();
            assertEquals(20, l2sl);
            l2.iSetReq(0, s0bs).call();
            l2sl = l2.getSerializedLength();
            assertEquals(24, l2sl);
            l2.iRemoveReq(0).call();
            l2sl = l2.getSerializedLength();
            assertEquals(16, l2sl);
            l2.emptyReq().call();
            l2sl = l2.getSerializedLength();
            assertEquals(8, l2sl);
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
