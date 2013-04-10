package org.agilewiki.incdes;

import junit.framework.TestCase;

public class DoubleTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            PADouble double1 = IncDesFactories.createPADouble(factoryLocator, null, null);
            PADouble double2 = (PADouble) double1.copy(null);
            double2.setDoubleReq(1.d).call();
            PADouble double3 = (PADouble) double2.copy(null);

            int sl = double1.getSerializedLength();
            assertEquals(8, sl);
            sl = double2.getSerializedLength();
            assertEquals(8, sl);
            sl = double3.getSerializedLength();
            assertEquals(8, sl);

            double v = double1.getDoubleReq().call();
            assertEquals(0.D, v);
            v = double2.getDoubleReq().call();
            assertEquals(1.D, v);
            v = double3.getDoubleReq().call();
            assertEquals(1.D, v);

            Box box = IncDesFactories.createBox(factoryLocator, null, null);
            box.setIncDesReq(IncDesFactories.PADOUBLE).call();
            PADouble rpa = (PADouble) box.resolvePathnameReq("0").call();
            v = rpa.getDoubleReq().call();
            assertEquals(0.D, v);
            rpa.setDoubleReq(-1d).call();
            rpa = (PADouble) box.resolvePathnameReq("0").call();
            v = rpa.getDoubleReq().call();
            assertEquals(-1.D, v);

        } finally {
            jaBundleContext.stop(0);
        }
    }
}
