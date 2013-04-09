package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;
import org.agilewiki.incdes.impl.scalar.vlens.RootImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.collection.IGet;
import org.agilewiki.jid.collection.vlenc.IAdd;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.flens.integer.SetInteger;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class SumTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        Sum.register(factoryLocator);
        JAFuture future = new JAFuture();

        RootImpl root = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
        (new SetActor("sum")).send(future, root);
        Sum sum = (Sum) (new ResolvePathname("0")).send(future, root);
        IAdd iAdd = new IAdd(-1);
        IGet iGet = new IGet(-1);
        iAdd.send(future, sum);
        PAIntegerImpl ij0 = (PAIntegerImpl) iGet.send(future, sum);
        (new SetInteger(1)).send(future, ij0);
        iAdd.send(future, sum);
        PAIntegerImpl ij1 = (PAIntegerImpl) iGet.send(future, sum);
        (new SetInteger(2)).send(future, ij1);
        iAdd.send(future, sum);
        PAIntegerImpl ij2 = (PAIntegerImpl) iGet.send(future, sum);
        (new SetInteger(3)).send(future, ij2);
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootImpl root2 = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a);

        jaBundleContext.stop(0);
    }
}
