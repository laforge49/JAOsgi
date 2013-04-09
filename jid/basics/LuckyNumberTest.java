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
import org.agilewiki.jid.scalar.flens.integer.SetInteger;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class LuckyNumberTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        LuckyNumber.register(factoryLocator);
        JAFuture future = new JAFuture();

        RootImpl root = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
        (new SetActor("lucky number")).send(future, root);
        Actor a = (new ResolvePathname("0")).send(future, root);
        (new SetInteger(7)).send(future, a);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootImpl root2 = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a2 = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a2);

        jaBundleContext.stop(0);
    }
}
