package org.agilewiki.incdes;

import junit.framework.TestCase;

public class BytesTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            Bytes bytes1 = IncDesFactories.createBytes(factoryLocator, null, null);
            Bytes bytes2 = (Bytes) bytes1.copyJIDReq(null).call();
            bytes2.setBytesReq(new byte[3]).call();
            Bytes bytes3 = (Bytes) bytes2.copyJIDReq(null).call();

            int sl = bytes1.getSerializedLength();
            assertEquals(4, sl);
            sl = bytes2.getSerializedLength();
            assertEquals(7, sl);
            sl = bytes3.getSerializedLength();
            assertEquals(7, sl);

            assertNull(bytes1.getBytesReq().call());
            assertEquals(3, bytes2.getBytesReq().call().length);
            assertEquals(3, bytes3.getBytesReq().call().length);

            Box box = IncDesFactories.createBox(factoryLocator, null, null);
            box.setIncDesReq(IncDesFactories.BYTES_JID_TYPE).call();
            Bytes rpa = (Bytes) box.resolvePathnameReq("0").call();
            assertNull(rpa.getBytesReq().call());
            assertTrue(rpa.makeBytesReq(new byte[0]).call());
            assertFalse(rpa.makeBytesReq(new byte[99]).call());
            rpa = (Bytes) box.resolvePathnameReq("0").call();
            assertEquals(0, rpa.getBytesReq().call().length);
            rpa.clearReq().call();
            assertNull(rpa.getBytesReq().call());

        } finally {
            jaBundleContext.stop(0);
        }
    }
}
