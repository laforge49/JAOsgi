package org.agilewiki.jid.basics;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.incdes.impl.scalar.vlens.RootImpl;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.GetSerializedBytes;
import org.agilewiki.jid.ResolvePathname;
import org.agilewiki.jid.collection.vlenc.map.KGet;
import org.agilewiki.jid.collection.vlenc.map.KMake;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class UsersTest extends TestCase {
    public void test()
            throws Exception {
        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        Users.register(factoryLocator);
        JAFuture future = new JAFuture();

        RootImpl root = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
        (new SetActor("users")).send(future, root);
        Users users = (Users) (new ResolvePathname("0")).send(future, root);
        new KMake<String, User>("John").send(future, users);
        PAStringImpl jEmail = (PAStringImpl) new KGet<String, User>("John").send(future, users);
        jEmail.setValue("john123@gmail.com");
        new KMake<String, User>("Sam").send(future, users);
        PAStringImpl sEmail = (PAStringImpl) new KGet<String, User>("Sam").send(future, users);
        sEmail.setValue("sammyjr@yahoo.com");
        new KMake<String, User>("Fred").send(future, users);
        PAStringImpl fEmail = (PAStringImpl) new KGet<String, User>("Fred").send(future, users);
        fEmail.setValue("fredk@gmail.com");
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootImpl root2 = (RootImpl) factoryLocator.newJid(PAFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a);

        jaBundleContext.stop(0);
    }
}
