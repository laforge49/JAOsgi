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

            /*
            Box jidJid2 = Box.create(factoryLocator, null, null);
            sl = GetSerializedLength.req.send(future, jidJid2);
            assertEquals(4, sl);

            SetActor sjvj = new SetActor(IncDesFactories.INCDES);
            sjvj.send(future, jidJid2);
            MakeActor mjvj = new MakeActor(IncDesFactories.INCDES);
            boolean made = mjvj.send(future, jidJid2);
            assertEquals(false, made);
            IncDes jidJid2a = GetActor.req.send(future, jidJid2);
            assertNotNull(jidJid2a);
            sl = GetSerializedLength.req.send(future, jidJid2a);
            assertEquals(0, sl);
            sl = GetSerializedLength.req.send(future, jidJid2);
            assertEquals(52, sl);
            rpa = (new ResolvePathname("")).send(future, jidJid2);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid2);
            rpa = (new ResolvePathname("0")).send(future, jidJid2);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid2a);
            IncDes jidJid22 = (new CopyJID()).send(future, jidJid2);
            Clear.req.send(future, jidJid2);
            sl = GetSerializedLength.req.send(future, jidJid2);
            assertEquals(4, sl);
            jidJid2a = GetActor.req.send(future, jidJid2);
            assertNull(jidJid2a);
            assertNotNull(jidJid22);
            sl = GetSerializedLength.req.send(future, jidJid22);
            assertEquals(52, sl);
            rpa = (new ResolvePathname("")).send(future, jidJid22);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid22);
            rpa = (new ResolvePathname("0")).send(future, jidJid22);
            assertNotNull(rpa);
            sl = GetSerializedLength.req.send(future, rpa);
            assertEquals(0, sl);

            Box jidJid3 = Box.create(factoryLocator, null, null);
            sl = GetSerializedLength.req.send(future, jidJid3);
            assertEquals(4, sl);
            MakeActor mjvjj = new MakeActor(IncDesFactories.BOX);
            made = mjvjj.send(future, jidJid3);
            assertEquals(true, made);
            made = mjvjj.send(future, jidJid3);
            assertEquals(false, made);
            IncDes jidJid3a = GetActor.req.send(future, jidJid3);
            assertNotNull(jidJid3a);
            sl = GetSerializedLength.req.send(future, jidJid3a);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, jidJid3);
            assertEquals(68, sl);
            made = mjvj.send(future, jidJid3a);
            assertEquals(true, made);
            made = mjvj.send(future, jidJid3a);
            assertEquals(false, made);
            IncDes jidJid3b = GetActor.req.send(future, jidJid3a);
            assertNotNull(jidJid3b);
            sl = GetSerializedLength.req.send(future, jidJid3b);
            assertEquals(0, sl);
            sl = GetSerializedLength.req.send(future, jidJid3a);
            assertEquals(52, sl);
            sl = GetSerializedLength.req.send(future, jidJid3);
            assertEquals(116, sl);
            rpa = (new ResolvePathname("")).send(future, jidJid3);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid3);
            rpa = (new ResolvePathname("0")).send(future, jidJid3);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid3a);
            rpa = (new ResolvePathname("0/0")).send(future, jidJid3);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid3b);
            IncDes jidJid33 = (new CopyJID()).send(future, jidJid3);
            Clear.req.send(future, jidJid3a);
            sl = GetSerializedLength.req.send(future, jidJid3a);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, jidJid3);
            assertEquals(68, sl);
            jidJid3b = GetActor.req.send(future, jidJid3a);
            assertNull(jidJid2a);
            IncDes jidJid3aa = GetActor.req.send(future, jidJid3);
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
            assertEquals(52, sl);
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
