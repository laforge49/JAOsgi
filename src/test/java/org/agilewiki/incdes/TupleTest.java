package org.agilewiki.incdes;

import junit.framework.TestCase;
import org.agilewiki.pactor.Mailbox;

public class TupleTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            IncDesFactories.registerTupleFactory(factoryLocator,
                    "sst", IncDesFactories.PASTRING, IncDesFactories.PASTRING);
            Factory tjf = factoryLocator.getFactory("sst");
            Mailbox mailbox = factoryLocator.getMailboxFactory().createMailbox();
            Tuple t0 = (Tuple) tjf.newActor(mailbox, factoryLocator);
            PAString e0 = (PAString) t0.iGetReq(0).call();
            assertNull(e0.getStringReq().call());
            PAString e1 = (PAString) t0.iGetReq(1).call();
            assertNull(e1.getStringReq().call());
            e0.setStringReq("Apples").call();
            assertEquals("Apples", e0.getStringReq().call());
            e1.setStringReq("Oranges").call();
            assertEquals("Oranges", e1.getStringReq().call());
            Tuple t1 = (Tuple) t0.copyReq(null).call();
            PAString f0 = (PAString) t1.resolvePathnameReq("0").call();
            assertEquals("Apples", f0.getStringReq().call());
            PAString f1 = (PAString) t1.resolvePathnameReq("1").call();
            assertEquals("Oranges", f1.getStringReq().call());

            PAString paString1 = IncDesFactories.createPAString(factoryLocator, null, null);
            paString1.setStringReq("Peaches").call();
            byte[] sb = paString1.getSerializedBytesReq().call();
            t1.iSetReq(1, sb).call();
            PAString f1b = (PAString) t1.resolvePathnameReq("1").call();
            assertEquals("Peaches", f1b.getStringReq().call());
        } finally {
            factoryLocator.close();
        }
    }
}
