package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.impl.scalar.vlens.RootImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.flens.integer.SetInteger;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class LuckyNumberTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocatorImpl factoryLocator = IncDesFactories.createFactoryLocator(1);
        Context jaBundleContext = Context.get(factoryLocator);
        LuckyNumber.register(factoryLocator);
        JAFuture future = new JAFuture();

        RootImpl root = (RootImpl) factoryLocator.newJid(IncDesFactories.ROOT);
        (new SetActor("lucky number")).send(future, root);
        Actor a = (new ResolvePathname("0")).send(future, root);
        (new SetInteger(7)).send(future, a);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootImpl root2 = (RootImpl) factoryLocator.newJid(IncDesFactories.ROOT);
        root2.load(rootBytes);
        Actor a2 = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a2);

        jaBundleContext.stop(0);
    }
}
