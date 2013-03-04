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
import org.agilewiki.jid.scalar.vlens.actor.RootJid;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

public class UsersTest extends TestCase {
    public void test()
            throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        Users.register(factoryLocator);
        JAFuture future = new JAFuture();

        RootJid root = (RootJid) factoryLocator.newActor(JidFactories.ROOT_JID_TYPE);
        (new SetActor("users")).send(future, root);
        Users users = (Users) (new ResolvePathname("0")).send(future, root);
        new KMake<String, User>("John").send(future, users);
        StringJid jEmail = (StringJid) new KGet<String, User>("John").send(future, users);
        jEmail.setValue("john123@gmail.com");
        new KMake<String, User>("Sam").send(future, users);
        StringJid sEmail = (StringJid) new KGet<String, User>("Sam").send(future, users);
        sEmail.setValue("sammyjr@yahoo.com");
        new KMake<String, User>("Fred").send(future, users);
        StringJid fEmail = (StringJid) new KGet<String, User>("Fred").send(future, users);
        fEmail.setValue("fredk@gmail.com");
        byte[] rootBytes = GetSerializedBytes.req.send(future, root);

        RootJid root2 = (RootJid) factoryLocator.newActor(JidFactories.ROOT_JID_TYPE);
        root2.load(rootBytes);
        Actor a = (new ResolvePathname("0")).send(future, root2);
        Proc.req.send(future, a);

        jaBundleContext.stop(0);
    }
}
