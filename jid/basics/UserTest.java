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
import org.agilewiki.jid.scalar.vlens.string.SetString;

public class UserTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocatorImpl factoryLocator = IncDesFactories.createFactoryLocator(1);
        Context jaBundleContext = Context.get(factoryLocator);
        User.register(factoryLocator);
        JAFuture future = new JAFuture();

        RootImpl root = (RootImpl) factoryLocator.newJid(IncDesFactories.ROOT);
        (new SetActor("user")).send(future, root);
        Actor name = (new ResolvePathname("0/0")).send(future, root);
        (new SetString("Frank")).send(future, name);
        Actor age = (new ResolvePathname("0/1")).send(future, root);
        (new SetInteger(30)).send(future, age);
        Actor location = (new ResolvePathname("0/2")).send(future, root);
        (new SetString("Bangalore")).send(future, location);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootImpl root2 = (RootImpl) factoryLocator.newJid(IncDesFactories.ROOT);
        root2.load(rootBytes);
        Actor user = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, user);

        jaBundleContext.stop(0);
    }
}
