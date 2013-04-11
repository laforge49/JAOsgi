package org.agilewiki.incdes;

import junit.framework.TestCase;

public class RootTest extends TestCase {
    public void test() throws Exception {
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            Factory rootFactory = factoryLocator.getFactory(IncDesFactories.ROOT);
            Root root1 = (Root) rootFactory.newActor(factoryLocator.getMailbox(), factoryLocator);
            int sl = root1.getSerializedLength();
            assertEquals(56, sl);
            root1.clearReq().call();
            sl = root1.getSerializedLength();
            assertEquals(56, sl);
            IncDes rootJid1a = root1.getIncDesReq().call();
            assertNull(rootJid1a);
            IncDes rpa = root1.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, root1);
            rpa = root1.resolvePathnameReq("0").call();
            assertNull(rpa);
            Root root11 = (Root) root1.copyReq(null).call();
            assertNotNull(root11);
            sl = root11.getSerializedLength();
            assertEquals(56, sl);
            rpa = root11.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, root11);
            rpa = root11.resolvePathnameReq("0").call();
            assertNull(rpa);

            Factory stringAFactory = factoryLocator.getFactory(IncDesFactories.PASTRING);
            PAString paString1 = (PAString) stringAFactory.newActor(factoryLocator.getMailbox(), factoryLocator);
            paString1.setStringReq("abc").call();
            byte[] sb = paString1.getSerializedBytesReq().call();
            root1.setIncDesReq(paString1.getType(), sb).call();
            PAString sj = (PAString) root1.getIncDesReq().call();
            assertEquals("abc", sj.getStringReq().call());

            Root root2 = (Root) rootFactory.newActor(factoryLocator.getMailbox(), factoryLocator);
            sl = root2.getSerializedLength();
            assertEquals(56, sl);
            root2.setIncDesReq(IncDesFactories.INCDES).call();
            boolean made = root2.makeIncDesReq(IncDesFactories.INCDES).call();
            assertEquals(false, made);
            IncDes incDes2a = root2.getIncDesReq().call();
            assertNotNull(incDes2a);
            sl = incDes2a.getSerializedLength();
            assertEquals(0, sl);
            sl = root2.getSerializedLength();
            assertEquals(110, sl);
            rpa = root2.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, root2);
            rpa = root2.resolvePathnameReq("0").call();
            assertNotNull(rpa);
            assertEquals(rpa, incDes2a);
            IncDes root22 = (Root) root2.copyReq(null).call();
            root2.clearReq().call();
            sl = root2.getSerializedLength();
            assertEquals(56, sl);
            incDes2a = root2.getIncDesReq().call();
            assertNull(incDes2a);
            assertNotNull(root22);
            sl = root22.getSerializedLength();
            assertEquals(110, sl);
            rpa = root22.resolvePathnameReq("").call();
            assertNotNull(rpa);
            assertEquals(rpa, root22);
            rpa = root22.resolvePathnameReq("0").call();
            assertNotNull(rpa);
            sl = rpa.getSerializedLength();
            assertEquals(0, sl);

        } finally {
            jaBundleContext.stop(0);
        }
    }
}
