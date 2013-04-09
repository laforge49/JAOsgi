package org.agilewiki.jid.scalar.flens.integer;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class IntegerTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            PAIntegerImpl int1 = (PAIntegerImpl) factoryLocator.newJid(PAFactories.INTEGER_JID_TYPE);
            PAIntegerImpl int2 = (PAIntegerImpl) (new CopyJID()).send(future, int1);
            (new SetInteger(1)).send(future, int2);
            PAIntegerImpl int3 = (PAIntegerImpl) (new CopyJID()).send(future, int2);

            int sl = GetSerializedLength.req.send(future, int1);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, int2);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, int3);
            assertEquals(4, sl);

            int v = GetInteger.req.send(future, int1);
            assertEquals(0, v);
            v = GetInteger.req.send(future, int2);
            assertEquals(1, v);
            v = GetInteger.req.send(future, int3);
            assertEquals(1, v);

            BoxImpl jidJid1 = BoxImpl.create(factoryLocator, null, null);
            SetActor sjvi = new SetActor(PAFactories.INTEGER_JID_TYPE);
            sjvi.send(future, jidJid1);
            PAIntegerImpl rpa = (PAIntegerImpl) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetInteger.req.send(future, rpa);
            assertEquals(0, v);
            (new SetInteger(-1)).send(future, rpa);
            rpa = (PAIntegerImpl) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetInteger.req.send(future, rpa);
            assertEquals(-1, v);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
