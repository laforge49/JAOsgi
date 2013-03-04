package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.collection.vlenc.map.KGet;
import org.agilewiki.jid.collection.vlenc.map.KMake;
import org.agilewiki.jid.scalar.vlens.actor.ActorJid;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class BlobTest extends TestCase {
    public void test()
            throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        Blob.register(factoryLocator);
        HelloWorld.register(factoryLocator);
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newActor(JidFactories.ROOT_JID_TYPE);
        (new SetActor("blob")).send(future, root);
        Blob blob = (Blob) (new ResolvePathname("0")).send(future, root);
        new KMake<String, ActorJid>("fun").send(future, blob);
        ActorJid fun = (ActorJid) new KGet<String, ActorJid>("fun").send(future, blob);
        (new SetActor("hi")).send(future, fun);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootJid root2 = (RootJid) factoryLocator.newActor(JidFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a);

        jaBundleContext.stop(0);
    }
}
