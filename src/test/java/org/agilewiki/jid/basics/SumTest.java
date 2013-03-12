package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.collection.IGet;
import org.agilewiki.jid.collection.vlenc.IAdd;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;
import org.agilewiki.jid.scalar.flens.integer.SetInteger;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class SumTest extends TestCase {
    public void test()
            throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        Sum.register(factoryLocator);
        JAFuture future = new JAFuture();

        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        (new SetActor("sum")).send(future, root);
        Sum sum = (Sum) (new ResolvePathname("0")).send(future, root);
        IAdd iAdd = new IAdd(-1);
        IGet iGet = new IGet(-1);
        iAdd.send(future, sum);
        IntegerJid ij0 = (IntegerJid) iGet.send(future, sum);
        (new SetInteger(1)).send(future, ij0);
        iAdd.send(future, sum);
        IntegerJid ij1 = (IntegerJid) iGet.send(future, sum);
        (new SetInteger(2)).send(future, ij1);
        iAdd.send(future, sum);
        IntegerJid ij2 = (IntegerJid) iGet.send(future, sum);
        (new SetInteger(3)).send(future, ij2);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootJid root2 = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a);

        jaBundleContext.stop(0);
    }
}
