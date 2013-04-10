package org.agilewiki.incdes.scalar;

import junit.framework.TestCase;
import org.agilewiki.incdes.*;
import org.agilewiki.pactor.Mailbox;

public class BooleanTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            Mailbox mailbox = jaBundleContext.getMailbox();
            PABoolean boolean1 = IncDesFactories.createPABoolean(factoryLocator, mailbox, null);
            PABoolean boolean2 = (PABoolean) boolean1.copyJIDReq(mailbox).call();
            boolean2.setBooleanReq(true).call();
            PABoolean boolean3 = (PABoolean) boolean2.copyJIDReq(mailbox).call();

            int sl = boolean1.getSerializedLength();
            assertEquals(1, sl);
            sl = boolean2.getSerializedLength();
            assertEquals(1, sl);
            sl = boolean3.getSerializedLength();
            assertEquals(1, sl);

            assertFalse(boolean1.getBooleanReq().call());
            assertTrue(boolean2.getBooleanReq().call());
            assertTrue(boolean3.getBooleanReq().call());

            Box box = IncDesFactories.createBox(factoryLocator, mailbox, factoryLocator);
            box.setIncDesReq(IncDesFactories.BOOLEAN_JID_TYPE).call();
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
