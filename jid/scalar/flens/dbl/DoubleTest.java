package org.agilewiki.jid.scalar.flens.dbl;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.flens.PADoubleImpl;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class DoubleTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            PADoubleImpl double1 = (PADoubleImpl) factoryLocator.newJid(PAFactories.DOUBLE_JID_TYPE);
            PADoubleImpl double2 = (PADoubleImpl) (new CopyJID()).send(future, double1);
            (new SetDouble(1.D)).send(future, double2);
            PADoubleImpl double3 = (PADoubleImpl) (new CopyJID()).send(future, double2);

            int sl = GetSerializedLength.req.send(future, double1);
            assertEquals(8, sl);
            sl = GetSerializedLength.req.send(future, double2);
            assertEquals(8, sl);
            sl = GetSerializedLength.req.send(future, double3);
            assertEquals(8, sl);

            double v = GetDouble.req.send(future, double1);
            assertEquals(0.D, v);
            v = GetDouble.req.send(future, double2);
            assertEquals(1.D, v);
            v = GetDouble.req.send(future, double3);
            assertEquals(1.D, v);

            BoxImpl jidJid1 = BoxImpl.create(factoryLocator, null, null);
            SetActor sjvl = new SetActor(PAFactories.DOUBLE_JID_TYPE);
            sjvl.send(future, jidJid1);
            PADoubleImpl rpa = (PADoubleImpl) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetDouble.req.send(future, rpa);
            assertEquals(0.D, v);
            (new SetDouble(-1.D)).send(future, rpa);
            rpa = (PADoubleImpl) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetDouble.req.send(future, rpa);
            assertEquals(-1.D, v);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
