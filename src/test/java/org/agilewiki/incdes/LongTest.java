package org.agilewiki.incdes;

import junit.framework.TestCase;

public class LongTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PALong long1 = IncDesFactories.createPALong(factoryLocator, null, null);
            PALong long2 = (PALong) long1.copyReq(null).call();
            long2.setLongReq(1L).call();
            PALong long3 = (PALong) long2.copyReq(null).call();

            int sl = long1.getSerializedLength();
            assertEquals(8, sl);
            sl = long2.getSerializedLength();
            assertEquals(8, sl);
            sl = long3.getSerializedLength();
            assertEquals(8, sl);

            long v = long1.getLongReq().call();
            assertEquals(0L, v);
            v = long2.getLongReq().call();
            assertEquals(1L, v);
            v = long3.getLongReq().call();
            assertEquals(1L, v);

            Box box = IncDesFactories.createBox(factoryLocator, null, null);
            box.setIncDesReq(IncDesFactories.PALONG).call();
            PALong rpa = (PALong) box.resolvePathnameReq("0").call();
            v = rpa.getLongReq().call();
            assertEquals(0L, v);
            rpa.setLongReq(-1000000000000L).call();
            rpa = (PALong) box.resolvePathnameReq("0").call();
            v = rpa.getLongReq().call();
            assertEquals(-1000000000000L, v);

        } finally {
            jaBundleContext.stop(0);
        }
    }
}
