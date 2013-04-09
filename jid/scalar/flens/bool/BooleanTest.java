package org.agilewiki.jid.scalar.flens.bool;

import junit.framework.TestCase;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.impl.scalar.flens.PABooleanImpl;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class BooleanTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = IncDesFactories.createFactoryLocator(1);
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            PABooleanImpl boolean1 = (PABooleanImpl) factoryLocator.newJid(IncDesFactories.BOOLEAN_JID_TYPE);
            PABooleanImpl boolean2 = (PABooleanImpl) (new CopyJID()).send(future, boolean1);
            (new SetBoolean(true)).send(future, boolean2);
            PABooleanImpl boolean3 = (PABooleanImpl) (new CopyJID()).send(future, boolean2);

            int sl = GetSerializedLength.req.send(future, boolean1);
            assertEquals(1, sl);
            sl = GetSerializedLength.req.send(future, boolean2);
            assertEquals(1, sl);
            sl = GetSerializedLength.req.send(future, boolean3);
            assertEquals(1, sl);

            assertFalse(GetBoolean.req.send(future, boolean1));
            assertTrue(GetBoolean.req.send(future, boolean2));
            assertTrue(GetBoolean.req.send(future, boolean3));

            BoxImpl jidJid1 = BoxImpl.create(factoryLocator, null, null);
            SetActor sjvb = new SetActor(IncDesFactories.BOOLEAN_JID_TYPE);
            sjvb.send(future, jidJid1);
            PABooleanImpl rpa = (PABooleanImpl) (new ResolvePathname("0")).send(future, jidJid1);
            assertFalse(GetBoolean.req.send(future, rpa));
            (new SetBoolean(true)).send(future, rpa);
            rpa = (PABooleanImpl) (new ResolvePathname("0")).send(future, jidJid1);
            assertTrue(GetBoolean.req.send(future, rpa));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
