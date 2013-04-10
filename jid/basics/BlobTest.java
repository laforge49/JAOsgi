package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.impl.scalar.vlens.BoxImpl;
import org.agilewiki.incdes.impl.scalar.vlens.RootImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.collection.vlenc.map.KGet;
import org.agilewiki.jid.collection.vlenc.map.KMake;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class BlobTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocatorImpl factoryLocator = IncDesFactories.createFactoryLocator(1);
        Context jaBundleContext = Context.get(factoryLocator);
        Blob.register(factoryLocator);
        HelloWorld.register(factoryLocator);
        JAFuture future = new JAFuture();
        RootImpl root = (RootImpl) factoryLocator.newJid(IncDesFactories.ROOT);
        (new SetActor("blob")).send(future, root);
        Blob blob = (Blob) (new ResolvePathname("0")).send(future, root);
        new KMake<String, BoxImpl>("fun").send(future, blob);
        BoxImpl fun = (BoxImpl) new KGet<String, BoxImpl>("fun").send(future, blob);
        (new SetActor("hi")).send(future, fun);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootImpl root2 = (RootImpl) factoryLocator.newJid(IncDesFactories.ROOT);
        root2.load(rootBytes);
        Actor a = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a);

        jaBundleContext.stop(0);
    }
}
