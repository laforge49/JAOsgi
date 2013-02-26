package org.agilewiki.jid.scalar.flens.bool;

import junit.framework.TestCase;
import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jaosgi.JABundleContext;
import org.agilewiki.jaosgi.JAFactoryLocator;
import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class BooleanTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            BooleanJid boolean1 = (BooleanJid) factoryLocator.newActor(JidFactories.BOOLEAN_JID_TYPE);
            BooleanJid boolean2 = (BooleanJid) (new CopyJID()).send(future, boolean1);
            (new SetBoolean(true)).send(future, boolean2);
            BooleanJid boolean3 = (BooleanJid) (new CopyJID()).send(future, boolean2);

            int sl = GetSerializedLength.req.send(future, boolean1);
            assertEquals(1, sl);
            sl = GetSerializedLength.req.send(future, boolean2);
            assertEquals(1, sl);
            sl = GetSerializedLength.req.send(future, boolean3);
            assertEquals(1, sl);

            assertFalse(GetBoolean.req.send(future, boolean1));
            assertTrue(GetBoolean.req.send(future, boolean2));
            assertTrue(GetBoolean.req.send(future, boolean3));

            Actor jidJid1 = factoryLocator.newActor(JidFactories.ACTOR_JID_TYPE);
            SetActor sjvb = new SetActor(JidFactories.BOOLEAN_JID_TYPE);
            sjvb.send(future, jidJid1);
            BooleanJid rpa = (BooleanJid) (new ResolvePathname("0")).send(future, jidJid1);
            assertFalse(GetBoolean.req.send(future, rpa));
            (new SetBoolean(true)).send(future, rpa);
            rpa = (BooleanJid) (new ResolvePathname("0")).send(future, jidJid1);
            assertTrue(GetBoolean.req.send(future, rpa));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}