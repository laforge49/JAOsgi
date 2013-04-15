package org.agilewiki.incdes;

import junit.framework.TestCase;

public class FloatTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            PAFloat float1 = IncDesFactories.createPAFloat(factoryLocator, null, null);
            PAFloat float2 = (PAFloat) float1.copyReq(null).call();
            float2.setFloatReq(1.0f).call();
            PAFloat float3 = (PAFloat) float2.copyReq(null).call();

            int sl = float1.getSerializedLength();
            assertEquals(4, sl);
            sl = float2.getSerializedLength();
            assertEquals(4, sl);
            sl = float3.getSerializedLength();
            assertEquals(4, sl);

            float v = float1.getFloatReq().call();
            assertEquals(0.f, v);
            v = float2.getFloatReq().call();
            assertEquals(1.f, v);
            v = float3.getFloatReq().call();
            assertEquals(1.f, v);

            Box box = IncDesFactories.createBox(factoryLocator, null, null);
            box.setIncDesReq(IncDesFactories.PAFLOAT).call();
            PAFloat rpa = (PAFloat) box.resolvePathnameReq("0").call();
            v = rpa.getFloatReq().call();
            assertEquals(0.f, v);
            rpa.setFloatReq(-1.0f).call();
            rpa = (PAFloat) box.resolvePathnameReq("0").call();
            v = rpa.getFloatReq().call();
            assertEquals(-1.f, v);

        } finally {
            factoryLocator.close();
        }
    }
}
