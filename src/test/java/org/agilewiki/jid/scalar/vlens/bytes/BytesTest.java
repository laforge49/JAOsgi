package org.agilewiki.jid.scalar.vlens.bytes;

import junit.framework.TestCase;
import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jaosgi.JABundleContext;
import org.agilewiki.jaosgi.JAFactoryLocator;
import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.scalar.Clear;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class BytesTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Actor bytes1 = factoryLocator.newActor(JidFactories.BYTES_JID_TYPE);
            Actor bytes2 = (new CopyJID()).send(future, bytes1);
            (new SetBytes(new byte[3])).send(future, bytes2);
            Actor bytes3 = (new CopyJID()).send(future, bytes2);

            int sl = GetSerializedLength.req.send(future, bytes1);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, bytes2);
            assertEquals(7, sl);
            sl = GetSerializedLength.req.send(future, bytes3);
            assertEquals(7, sl);

            assertNull(GetBytes.req.send(future, bytes1));
            assertEquals(3, GetBytes.req.send(future, bytes2).length);
            assertEquals(3, GetBytes.req.send(future, bytes3).length);

            Actor jidJid1 = factoryLocator.newActor(JidFactories.ACTOR_JID_TYPE);
            SetActor sjvbs = new SetActor(JidFactories.BYTES_JID_TYPE);
            sjvbs.send(future, jidJid1);
            Actor rpa = (new ResolvePathname("0")).send(future, jidJid1);
            assertNull(GetBytes.req.send(future, rpa));
            assertTrue((new MakeBytes(new byte[0])).send(future, rpa));
            assertFalse((new MakeBytes(new byte[99])).send(future, rpa));
            rpa = (new ResolvePathname("0")).send(future, jidJid1);
            assertEquals(0, GetBytes.req.send(future, rpa).length);
            Clear.req.sendEvent(rpa);
            assertNull(GetBytes.req.send(future, rpa));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
