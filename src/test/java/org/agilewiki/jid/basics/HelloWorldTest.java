package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class HelloWorldTest extends TestCase {
    public void test()
            throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        HelloWorld.register(factoryLocator);
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        (new SetActor("hi")).send(future, root);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootJid root2 = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a);

        jaBundleContext.stop(0);
    }
}
