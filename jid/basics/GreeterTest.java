package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.vlens.RootImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;
import org.agilewiki.jid.scalar.vlens.string.SetString;

public class GreeterTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        Greeter.register(factoryLocator, "hi greeter", "Hi");
        JAFuture future = new JAFuture();
        RootImpl root = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
        (new SetActor("hi greeter")).send(future, root);
        Actor a = (new ResolvePathname("0")).send(future, root);
        (new SetString("Frank")).send(future, a);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootImpl root2 = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a2 = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a2);

        jaBundleContext.stop(0);
    }
}
