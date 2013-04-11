package org.agilewiki.incdes;

import junit.framework.TestCase;

public class BoxTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            Factory boxAFactory = factoryLocator.getFactory(IncDesFactories.BOX);
            Box box1 = (Box) boxAFactory.newActor(factoryLocator.getMailbox(), factoryLocator);
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
            PAString string1 = (PAString) stringAFactory.newActor(factoryLocator.getMailbox(), factoryLocator);
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

            /*
            Box box3 = Box.create(factoryLocator, null, null);
            sl = GetSerializedLength.req.send(future, box3);
            assertEquals(4, sl);
            MakeActor makeBox = new MakeActor(IncDesFactories.BOX);
            made = makeBox.send(future, box3);
            assertEquals(true, made);
            made = makeBox.send(future, box3);
            assertEquals(false, made);
            IncDes jidJid3a = GetActor.req.send(future, box3);
            assertNotNull(jidJid3a);
            sl = GetSerializedLength.req.send(future, jidJid3a);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, box3);
            assertEquals(68, sl);
            made = makeIncDes.send(future, jidJid3a);
            assertEquals(true, made);
            made = makeIncDes.send(future, jidJid3a);
            assertEquals(false, made);
            IncDes jidJid3b = GetActor.req.send(future, jidJid3a);
            assertNotNull(jidJid3b);
            sl = GetSerializedLength.req.send(future, jidJid3b);
            assertEquals(0, sl);
            sl = GetSerializedLength.req.send(future, jidJid3a);
            assertEquals(58, sl);
            sl = GetSerializedLength.req.send(future, box3);
            assertEquals(116, sl);
            rpa = (new ResolvePathname("")).send(future, box3);
            assertNotNull(rpa);
            assertEquals(rpa, box3);
            rpa = (new ResolvePathname("0")).send(future, box3);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid3a);
            rpa = (new ResolvePathname("0/0")).send(future, box3);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid3b);
            IncDes jidJid33 = (new CopyJID()).send(future, box3);
            Clear.req.send(future, jidJid3a);
            sl = GetSerializedLength.req.send(future, jidJid3a);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, box3);
            assertEquals(68, sl);
            jidJid3b = GetActor.req.send(future, jidJid3a);
            assertNull(incDes2a);
            IncDes jidJid3aa = GetActor.req.send(future, box3);
            assertEquals(jidJid3a, jidJid3aa);
            assertNotNull(jidJid33);
            sl = GetSerializedLength.req.send(future, jidJid33);
            assertEquals(116, sl);
            rpa = (new ResolvePathname("")).send(future, jidJid33);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid33);
            rpa = (new ResolvePathname("0")).send(future, jidJid33);
            assertNotNull(rpa);
            sl = GetSerializedLength.req.send(future, rpa);
            assertEquals(58, sl);
            rpa = (new ResolvePathname("0/0")).send(future, jidJid33);
            assertNotNull(rpa);
            sl = GetSerializedLength.req.send(future, rpa);
            assertEquals(0, sl);
            */

        } finally {
            jaBundleContext.stop(0);
        }
    }
}
