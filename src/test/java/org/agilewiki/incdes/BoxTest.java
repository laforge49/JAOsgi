package org.agilewiki.incdes;

import junit.framework.TestCase;
import org.agilewiki.pactor.Mailbox;

public class BoxTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            Factory boxAFactory = factoryLocator.getFactory(IncDesFactories.BOX);
            Mailbox mailbox = factoryLocator.getMailboxFactory().createMailbox();
            Box box1 = (Box) boxAFactory.newActor(mailbox, factoryLocator);
            int sl = box1.getSerializedLength();
            assertEquals(4, sl);
            box1.clearReq().call();
            sl = box1.getSerializedLength();
            assertEquals(4, sl);
            IncDes incDes1a = box1.getIncDesReq().call();
            assertNull(incDes1a);
            IncDes rpa = box1.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, box1);
            rpa = box1.resolvePathnameReq("0").call();
            assertNull(rpa);
            IncDes incDes11 = box1.copyReq(null).call();
            assertNotNull(incDes11);
            sl = incDes11.getSerializedLength();
            assertEquals(4, sl);
            rpa = incDes11.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, incDes11);
            rpa = (IncDes) incDes11.resolvePathnameReq("0").call();
            assertNull(rpa);

            Factory stringAFactory = factoryLocator.getFactory(IncDesFactories.PASTRING);
            PAString string1 = (PAString) stringAFactory.newActor(mailbox, factoryLocator);
            string1.setStringReq("abc").call();
            byte[] sb = string1.getSerializedBytes();
            box1.setIncDesReq(string1.getType(), sb).call();
            PAString sj = (PAString) box1.getIncDesReq().call();
            assertEquals("abc", sj.getStringReq().call());

            Box box2 = IncDesFactories.createBox(factoryLocator, null, null);
            sl = box2.getSerializedLength();
            assertEquals(4, sl);

            box2.setIncDesReq(IncDesFactories.INCDES).call();
            boolean made = box2.makeIncDesReq(IncDesFactories.INCDES).call();
            assertEquals(false, made);
            IncDes incDes2a = box2.getIncDesReq().call();
            assertNotNull(incDes2a);
            sl = incDes2a.getSerializedLength();
            assertEquals(0, sl);
            sl = box2.getSerializedLength();
            assertEquals(58, sl);
            rpa = box2.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, box2);
            rpa = box2.resolvePathnameReq("0").call();
            assertNotNull(rpa);
            assertEquals(rpa, incDes2a);
            Box box22 = (Box) box2.copyReq(null).call();
            box2.clearReq().call();
            sl = box2.getSerializedLength();
            assertEquals(4, sl);
            incDes2a = box2.getIncDesReq().call();
            assertNull(incDes2a);
            assertNotNull(box22);
            sl = box22.getSerializedLength();
            assertEquals(58, sl);
            rpa = box22.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, box22);
            rpa = box22.resolvePathnameReq("0").call();
            assertNotNull(rpa);
            sl = rpa.getSerializedLength();
            assertEquals(0, sl);

            Box box3 = IncDesFactories.createBox(factoryLocator, null, null);
            sl = box3.getSerializedLength();
            assertEquals(4, sl);
            made = box3.makeIncDesReq(IncDesFactories.BOX).call();
            assertEquals(true, made);
            made = box3.makeIncDesReq(IncDesFactories.BOX).call();
            assertEquals(false, made);
            Box box3a = (Box) box3.getIncDesReq().call();
            assertNotNull(box3a);
            sl = box3a.getSerializedLength();
            assertEquals(4, sl);
            sl = box3.getSerializedLength();
            assertEquals(56, sl);
            made = box3a.makeIncDesReq(IncDesFactories.INCDES).call();
            assertEquals(true, made);
            made = box3a.makeIncDesReq(IncDesFactories.INCDES).call();
            assertEquals(false, made);
            IncDes incDes3b = box3a.getIncDesReq().call();
            assertNotNull(incDes3b);
            sl = incDes3b.getSerializedLength();
            assertEquals(0, sl);
            sl = box3a.getSerializedLength();
            assertEquals(58, sl);
            sl = box3.getSerializedLength();
            assertEquals(110, sl);
            rpa = box3.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, box3);
            rpa = box3.resolvePathnameReq("0").call();
            assertNotNull(rpa);
            assertEquals(rpa, box3a);
            rpa = box3.resolvePathnameReq("0/0").call();
            assertNotNull(rpa);
            assertEquals(rpa, incDes3b);
            Box box33 =(Box) box3.copyReq(null).call();
            box3a.clearReq().call();
            sl = box3a.getSerializedLength();
            assertEquals(4, sl);
            sl = box3.getSerializedLength();
            assertEquals(56, sl);
            incDes3b = box3a.getIncDesReq().call();
            assertNull(incDes3b);
            Box box3aa = (Box) box3.getIncDesReq().call();
            assertEquals(box3a, box3aa);
            assertNotNull(box33);
            sl = box33.getSerializedLength();
            assertEquals(110, sl);
            rpa = box33.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, box33);
            rpa = box33.resolvePathnameReq("0").call();
            assertNotNull(rpa);
            sl = rpa.getSerializedLength();
            assertEquals(58, sl);
            rpa = box33.resolvePathnameReq("0/0").call();
            assertNotNull(rpa);
            sl = rpa.getSerializedLength();
            assertEquals(0, sl);

        } finally {
            factoryLocator.close();
        }
    }
}
