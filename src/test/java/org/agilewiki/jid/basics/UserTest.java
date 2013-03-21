package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.flens.integer.SetInteger;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;
import org.agilewiki.jid.scalar.vlens.string.SetString;

public class UserTest extends TestCase {
    public void test()
            throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        User.register(factoryLocator);
        JAFuture future = new JAFuture();

        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        (new SetActor("user")).send(future, root);
        Actor name = (new ResolvePathname("0/0")).send(future, root);
        (new SetString("Frank")).send(future, name);
        Actor age = (new ResolvePathname("0/1")).send(future, root);
        (new SetInteger(30)).send(future, age);
        Actor location = (new ResolvePathname("0/2")).send(future, root);
        (new SetString("Bangalore")).send(future, location);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootJid root2 = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor user = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, user);

        jaBundleContext.stop(0);
    }
}
