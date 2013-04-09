package org.agilewiki.jid.scalar.vlens.string;

import junit.framework.TestCase;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.Clear;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class StringTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = IncDesFactories.createFactoryLocator(1);
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            PAStringImpl string1 = PAStringImpl.create(factoryLocator, null, null);
            PAStringImpl string2 = (PAStringImpl) (new CopyJID()).send(future, string1);
            (new SetString("abc")).send(future, string2);
            PAStringImpl string3 = (PAStringImpl) (new CopyJID()).send(future, string2);

            int sl = GetSerializedLength.req.send(future, string1);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, string2);
            assertEquals(10, sl);
            sl = GetSerializedLength.req.send(future, string3);
            assertEquals(10, sl);

            assertNull(GetString.req.send(future, string1));
            assertEquals("abc", GetString.req.send(future, string2));
            assertEquals("abc", GetString.req.send(future, string3));

            BoxImpl jidJid1 = BoxImpl.create(factoryLocator, null, null);
            SetActor sjvbs = new SetActor(IncDesFactories.STRING_JID_TYPE);
            sjvbs.send(future, jidJid1);
            PAStringImpl rpa = (PAStringImpl) (new ResolvePathname("0")).send(future, jidJid1);
            assertNull(GetString.req.send(future, rpa));
            assertTrue((new MakeString("")).send(future, rpa));
            assertFalse((new MakeString("Hello?")).send(future, rpa));
            rpa = (PAStringImpl) (new ResolvePathname("0")).send(future, jidJid1);
            assertEquals("", GetString.req.send(future, rpa));
            (new SetString("bye")).send(future, rpa);
            assertEquals("bye", GetString.req.send(future, rpa));
            sl = GetSerializedLength.req.send(future, rpa);
            assertEquals(10, sl);
            Clear.req.sendEvent(rpa);
            assertNull(GetString.req.send(future, rpa));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
