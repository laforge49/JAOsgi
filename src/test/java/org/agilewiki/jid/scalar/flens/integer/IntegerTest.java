package org.agilewiki.jid.scalar.flens.integer;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.vlens.actor.ActorJid;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class IntegerTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            IntegerJid int1 = (IntegerJid) factoryLocator.newJid(JidFactories.INTEGER_JID_TYPE);
            IntegerJid int2 = (IntegerJid) (new CopyJID()).send(future, int1);
            (new SetInteger(1)).send(future, int2);
            IntegerJid int3 = (IntegerJid) (new CopyJID()).send(future, int2);

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

            ActorJid jidJid1 = ActorJid.create(factoryLocator, null, null);
            SetActor sjvi = new SetActor(JidFactories.INTEGER_JID_TYPE);
            sjvi.send(future, jidJid1);
            IntegerJid rpa = (IntegerJid) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetInteger.req.send(future, rpa);
            assertEquals(0, v);
            (new SetInteger(-1)).send(future, rpa);
            rpa = (IntegerJid) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetInteger.req.send(future, rpa);
            assertEquals(-1, v);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
