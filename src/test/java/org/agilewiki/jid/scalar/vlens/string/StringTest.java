package org.agilewiki.jid.scalar.vlens.string;

import junit.framework.TestCase;
import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.scalar.Clear;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class StringTest extends TestCase {
    public void test() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            StringJid string1 = (StringJid) factoryLocator.newActor(JidFactories.STRING_JID_TYPE);
            StringJid string2 = (StringJid) (new CopyJID()).send(future, string1);
            (new SetString("abc")).send(future, string2);
            StringJid string3 = (StringJid) (new CopyJID()).send(future, string2);

            int sl = GetSerializedLength.req.send(future, string1);
            assertEquals(4, sl);
            sl = GetSerializedLength.req.send(future, string2);
            assertEquals(10, sl);
            sl = GetSerializedLength.req.send(future, string3);
            assertEquals(10, sl);

            assertNull(GetString.req.send(future, string1));
            assertEquals("abc", GetString.req.send(future, string2));
            assertEquals("abc", GetString.req.send(future, string3));

            Actor jidJid1 = factoryLocator.newActor(JidFactories.ACTOR_JID_TYPE);
            SetActor sjvbs = new SetActor(JidFactories.STRING_JID_TYPE);
            sjvbs.send(future, jidJid1);
            StringJid rpa = (StringJid) (new ResolvePathname("0")).send(future, jidJid1);
            assertNull(GetString.req.send(future, rpa));
            assertTrue((new MakeString("")).send(future, rpa));
            assertFalse((new MakeString("Hello?")).send(future, rpa));
            rpa = (StringJid) (new ResolvePathname("0")).send(future, jidJid1);
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
