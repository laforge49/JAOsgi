package org.agilewiki.incdes.scalar;

import junit.framework.TestCase;
import org.agilewiki.incdes.*;
import org.agilewiki.pactor.Mailbox;

public class IntegerTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            Mailbox mailbox = jaBundleContext.getMailbox();
            PAInteger int1 = IncDesFactories.createPAInteger(factoryLocator, mailbox, null);
            PAInteger int2 = (PAInteger) int1.copyJIDReq(mailbox).call();
            int2.setIntegerReq(1).call();
            PAInteger int3 = (PAInteger) int2.copyJIDReq(mailbox).call();

            int sl = int1.getSerializedLength();
            assertEquals(4, sl);
            sl = int2.getSerializedLength();
            assertEquals(4, sl);
            sl = int3.getSerializedLength();
            assertEquals(4, sl);

            int v = int1.getIntegerReq().call();
            assertEquals(0, v);
            v = int2.getIntegerReq().call();
            assertEquals(1, v);
            v = int3.getIntegerReq().call();
            assertEquals(1, v);

            Box box1 = IncDesFactories.createBox(factoryLocator, mailbox, factoryLocator);
            box1.setIncDesReq(IncDesFactories.INTEGER_JID_TYPE).call();
            PAInteger rpa = (PAInteger) box1.resolvePathnameReq("0").call();
            v = rpa.getIntegerReq().call();
            assertEquals(0, v);
            rpa.setIntegerReq(-1).call();
            rpa = (PAInteger) box1.resolvePathnameReq("0").call();
            v = rpa.getIntegerReq().call();
            assertEquals(-1, v);

        } finally {
            jaBundleContext.stop(0);
        }
    }
}
