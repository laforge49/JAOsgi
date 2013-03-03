package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jaosgi.JABundleContext;
import org.agilewiki.jaosgi.JAFactoryLocator;
import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;
import org.agilewiki.jid.scalar.vlens.string.SetString;

public class GreeterTest extends TestCase {
    public void test()
            throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        Greeter.register(factoryLocator, "hi greeter", "Hi");
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newActor(JidFactories.ROOT_JID_TYPE);
        (new SetActor("hi greeter")).send(future, root);
        Actor a = (new ResolvePathname("0")).send(future, root);
        (new SetString("Frank")).send(future, a);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootJid root2 = (RootJid) factoryLocator.newActor(JidFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a2 = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a2);

        jaBundleContext.stop(0);
    }
}
