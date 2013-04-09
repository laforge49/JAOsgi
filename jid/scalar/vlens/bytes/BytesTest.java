package org.agilewiki.jid.scalar.vlens.bytes;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.incdes.impl.scalar.vlens.BytesImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.Clear;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class BytesTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Actor bytes1 = BytesImpl.create(factoryLocator, null, null);
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

            BoxImpl jidJid1 = BoxImpl.create(factoryLocator, null, null);
            SetActor sjvbs = new SetActor(PAFactories.BYTES_JID_TYPE);
            sjvbs.send(future, jidJid1);
            BytesImpl rpa = (BytesImpl) (new ResolvePathname("0")).send(future, jidJid1);
            assertNull(GetBytes.req.send(future, rpa));
            assertTrue((new MakeBytes(new byte[0])).send(future, rpa));
            assertFalse((new MakeBytes(new byte[99])).send(future, rpa));
            rpa = (BytesImpl) (new ResolvePathname("0")).send(future, jidJid1);
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
