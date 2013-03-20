package org.agilewiki.jid.scalar.flens.lng;

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

public class LongTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            LongJid long1 = (LongJid) factoryLocator.newJid(JidFactories.LONG_JID_TYPE);
            LongJid long2 = (LongJid) (new CopyJID()).send(future, long1);
            (new SetLong(1L)).send(future, long2);
            LongJid float3 = (LongJid) (new CopyJID()).send(future, long2);

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

            ActorJid jidJid1 = ActorJid.create(factoryLocator, null, null);
            SetActor sjvl = new SetActor(JidFactories.LONG_JID_TYPE);
            sjvl.send(future, jidJid1);
            LongJid rpa = (LongJid) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetLong.req.send(future, rpa);
            assertEquals(0L, v);
            (new SetLong(-1000000000000L)).send(future, rpa);
            rpa = (LongJid) (new ResolvePathname("0")).send(future, jidJid1);
            v = GetLong.req.send(future, rpa);
            assertEquals(-1000000000000L, v);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
