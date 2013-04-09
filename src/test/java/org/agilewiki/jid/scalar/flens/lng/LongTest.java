package org.agilewiki.jid.scalar.flens.lng;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.incdes.impl.scalar.flens.PALongImpl;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class LongTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            PALongImpl long1 = (PALongImpl) factoryLocator.newJid(JidFactories.LONG_JID_TYPE);
            PALongImpl long2 = (PALongImpl) (new CopyJID()).send(future, long1);
            (new SetLong(1L)).send(future, long2);
            PALongImpl float3 = (PALongImpl) (new CopyJID()).send(future, long2);

            int sl = GetSerializedLength.req.send(future, long1);
            assertEquals(8, sl);
            sl = GetSerializedLength.req.send(future, long2);
            assertEquals(8, sl);
            sl = GetSerializedLength.req.send(future, float3);
            assertEquals(8, sl);

            long v = GetLong.req.send(future, long1);
            assertEquals(0L, v);
            v = GetLong.req.send(future, long2);
            assertEquals(1L, v);
            v = GetLong.req.send(future, float3);
            assertEquals(1L, v);

            BoxImpl jidJid1 = BoxImpl.create(factoryLocator, null, null);
            SetActor sjvl = new SetActor(JidFactories.LONG_JID_TYPE);
            sjvl.send(future, jidJid1);
            PALongImpl rpa = (PALongImpl) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetLong.req.send(future, rpa);
            assertEquals(0L, v);
            (new SetLong(-1000000000000L)).send(future, rpa);
            rpa = (PALongImpl) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetLong.req.send(future, rpa);
            assertEquals(-1000000000000L, v);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
