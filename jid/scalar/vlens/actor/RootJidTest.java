package org.agilewiki.jid.scalar.vlens.actor;

import junit.framework.TestCase;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.Context;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.CopyJID;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.GetSerializedLength;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.ActorFactory;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.Clear;
import org.agilewiki.jid.scalar.vlens.string.GetString;
import org.agilewiki.jid.scalar.vlens.string.SetString;

public class RootJidTest extends TestCase {
    public void test() throws Exception {
        FactoryLocatorImpl factoryLocator = IncDesFactories.createFactoryLocator(1);
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            RootJidFactory rootJidFactory = (RootJidFactory) factoryLocator.getJidFactory(IncDesFactories.ROOT_JID_TYPE);
            Actor rootJid1 = rootJidFactory.newActor(factoryLocator.getMailbox(), factoryLocator);
            int sl = GetSerializedLength.req.send(future, rootJid1);
            assertEquals(56, sl);
            Clear.req.send(future, rootJid1);
            sl = GetSerializedLength.req.send(future, rootJid1);
            assertEquals(56, sl);
            Actor rootJid1a = GetActor.req.send(future, rootJid1);
            assertNull(rootJid1a);
            Actor rpa = (new ResolvePathname("")).send(future, rootJid1);
            assertNotNull(rpa);
            assertEquals(rpa, rootJid1);
            rpa = (new ResolvePathname("0")).send(future, rootJid1);
            assertNull(rpa);
            Actor rootJid11 = (new CopyJID()).send(future, rootJid1);
            assertNotNull(rootJid11);
            sl = GetSerializedLength.req.send(future, rootJid11);
            assertEquals(56, sl);
            rpa = (new ResolvePathname("")).send(future, rootJid11);
            assertNotNull(rpa);
            assertEquals(rpa, rootJid11);
            rpa = (new ResolvePathname("0")).send(future, rootJid11);
            assertNull(rpa);

            ActorFactory stringJidAFactory = factoryLocator.getJidFactory(IncDesFactories.STRING_JID_TYPE);
            Actor string1 = stringJidAFactory.newActor(factoryLocator.getMailbox(), factoryLocator);
            (new SetString("abc")).send(future, string1);
            byte[] sb = GetSerializedBytes.req.send(future, string1);
            (new SetActorBytes(stringJidAFactory, sb)).send(future, rootJid1);
            Actor sj = GetActor.req.send(future, rootJid1);
            assertEquals("abc", GetString.req.send(future, sj));

            Actor rootJid2 = rootJidFactory.newActor(factoryLocator.getMailbox(), factoryLocator);
            sl = GetSerializedLength.req.send(future, rootJid2);
            assertEquals(56, sl);
            SetActor sjvj = new SetActor(IncDesFactories.JID_TYPE);
            sjvj.send(future, rootJid2);
            MakeActor mjvj = new MakeActor(IncDesFactories.JID_TYPE);
            boolean made = mjvj.send(future, rootJid2);
            assertEquals(false, made);
            Actor jidJid2a = GetActor.req.send(future, rootJid2);
            assertNotNull(jidJid2a);
            sl = GetSerializedLength.req.send(future, jidJid2a);
            assertEquals(0, sl);
            sl = GetSerializedLength.req.send(future, rootJid2);
            assertEquals(104, sl);
            rpa = (new ResolvePathname("")).send(future, rootJid2);
            assertNotNull(rpa);
            assertEquals(rpa, rootJid2);
            rpa = (new ResolvePathname("0")).send(future, rootJid2);
            assertNotNull(rpa);
            assertEquals(rpa, jidJid2a);
            Actor rootJid22 = (new CopyJID()).send(future, rootJid2);
            Clear.req.send(future, rootJid2);
            sl = GetSerializedLength.req.send(future, rootJid2);
            assertEquals(56, sl);
            jidJid2a = GetActor.req.send(future, rootJid2);
            assertNull(jidJid2a);
            assertNotNull(rootJid22);
            sl = GetSerializedLength.req.send(future, rootJid22);
            assertEquals(104, sl);
            rpa = (new ResolvePathname("")).send(future, rootJid22);
            assertNotNull(rpa);
            assertEquals(rpa, rootJid22);
            rpa = (new ResolvePathname("0")).send(future, rootJid22);
            assertNotNull(rpa);
            sl = GetSerializedLength.req.send(future, rpa);
            assertEquals(0, sl);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}