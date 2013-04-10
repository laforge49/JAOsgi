package org.agilewiki.incdes;

import junit.framework.TestCase;

public class BooleanTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PABoolean boolean1 = IncDesFactories.createPABoolean(factoryLocator, null, null);
            PABoolean boolean2 = (PABoolean) boolean1.copyReq(null).call();
            boolean2.setBooleanReq(true).call();
            PABoolean boolean3 = (PABoolean) boolean2.copyReq(null).call();

            int sl = boolean1.getSerializedLength();
            assertEquals(1, sl);
            sl = boolean2.getSerializedLength();
            assertEquals(1, sl);
            sl = boolean3.getSerializedLength();
            assertEquals(1, sl);

            assertFalse(boolean1.getBooleanReq().call());
            assertTrue(boolean2.getBooleanReq().call());
            assertTrue(boolean3.getBooleanReq().call());

            Box box = IncDesFactories.createBox(factoryLocator, null, null);
            box.setIncDesReq(IncDesFactories.PABOOLEAN).call();
            PABoolean rpa = (PABoolean) box.resolvePathnameReq("0").call();
            assertFalse(rpa.getBooleanReq().call());
            rpa.setBooleanReq(true).call();
            rpa = (PABoolean) box.resolvePathnameReq("0").call();
            assertTrue(rpa.getBooleanReq().call());

        } finally {
            jaBundleContext.stop(0);
        }
    }
}
