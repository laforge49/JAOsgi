package org.agilewiki.jid.scalar.flens.dbl;

import junit.framework.TestCase;
import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class DoubleTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            DoubleJid double1 = (DoubleJid) factoryLocator.newActor(JidFactories.DOUBLE_JID_TYPE);
            DoubleJid double2 = (DoubleJid) (new CopyJID()).send(future, double1);
            (new SetDouble(1.D)).send(future, double2);
            DoubleJid double3 = (DoubleJid) (new CopyJID()).send(future, double2);

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

            Actor jidJid1 = factoryLocator.newActor(JidFactories.ACTOR_JID_TYPE);
            SetActor sjvl = new SetActor(JidFactories.DOUBLE_JID_TYPE);
            sjvl.send(future, jidJid1);
            DoubleJid rpa = (DoubleJid) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetDouble.req.send(future, rpa);
            assertEquals(0.D, v);
            (new SetDouble(-1.D)).send(future, rpa);
            rpa = (DoubleJid) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetDouble.req.send(future, rpa);
            assertEquals(-1.D, v);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
