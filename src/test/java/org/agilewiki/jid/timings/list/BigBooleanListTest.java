package org.agilewiki.jid.timings.list;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.collection.vlenc.IAdd;
import org.agilewiki.incdes.impl.collection.vlenc.SList;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.vlens.actor.GetActor;
import org.agilewiki.incdes.impl.scalar.vlens.RootImpl;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class BigBooleanListTest extends TestCase {
    public void test1() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        JAFuture future = new JAFuture();
        RootImpl root = (RootImpl) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);

        SetActor setList = new SetActor(JidFactories.BOOLEAN_LIST_JID_TYPE);
        setList.send(future, root);
        SList list = (SList) GetActor.req.send(future, root);

        IAdd iAdd = new IAdd(-1);
        int i = 0;
        while (i < 100) {
            iAdd.send(future, list);
            i += 1;
        }

        jaBundleContext.stop(0);
    }

    public void test2() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        JAFuture future = new JAFuture();
        RootImpl root = (RootImpl) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);

        SetActor setList = new SetActor(JidFactories.BOOLEAN_LIST_JID_TYPE);
        setList.send(future, root);
        SList list = (SList) GetActor.req.send(future, root);

        BooleanAppender ba = new BooleanAppender();
        ba.initialize(root.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 10000;
        //ba.repeat = 10000;
        //Appends per second = 36,023,054

        ba.list = list;
        TimeBooleanAppender.req.send(future, ba);
        long t = TimeBooleanAppender.req.send(future, ba);
        System.out.println("list size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.count * ba.repeat / t;
            System.out.println("appends per second = " + ips);
        }

        jaBundleContext.stop(0);
    }

    public void test3() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        JAFuture future = new JAFuture();
        RootImpl root = (RootImpl) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);

        SetActor setList = new SetActor(JidFactories.BOOLEAN_LIST_JID_TYPE);
        setList.send(future, root);
        SList list = (SList) GetActor.req.send(future, root);

        BooleanAppender ba = new BooleanAppender();
        ba.initialize(root.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 10000;
        //ba.repeat = 10000;
        //Appends per second = 34,940,600

        list.initialCapacity = ba.count;

        ba.list = list;
        TimeBooleanAppender.req.send(future, ba);
        long t = TimeBooleanAppender.req.send(future, ba);
        System.out.println("list size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.count * ba.repeat / t;
            System.out.println("appends per second = " + ips);
        }

        jaBundleContext.stop(0);
    }

    public void test4() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        JAFuture future = new JAFuture();
        RootImpl root = (RootImpl) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);

        SetActor setList = new SetActor(JidFactories.BOOLEAN_LIST_JID_TYPE);
        setList.send(future, root);
        SList list = (SList) GetActor.req.send(future, root);

        BooleanSAppender ba = new BooleanSAppender();
        ba.initialize(root.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 10000;
        //ba.repeat = 10000;
        //Appends and serializes per second = 27,382,256

        list.initialCapacity = ba.count;

        ba.list = list;
        TimeBooleanSAppender.req.send(future, ba);
        long t = TimeBooleanSAppender.req.send(future, ba);
        System.out.println("list size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.count * ba.repeat / t;
            System.out.println("appends and serializes per second = " + ips);
        }

        jaBundleContext.stop(0);
    }

    public void test5() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        JAFuture future = new JAFuture();
        RootImpl root = (RootImpl) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);

        SetActor setList = new SetActor(JidFactories.BOOLEAN_LIST_JID_TYPE);
        setList.send(future, root);
        SList list = (SList) GetActor.req.send(future, root);

        BooleanUAppender ba = new BooleanUAppender();
        ba.initialize(root.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 10000;
        //ba.repeat = 10000;
        //Updates per second = 28,785,261

        list.initialCapacity = ba.count;

        ba.list = list;
        TimeBooleanUAppender.req.send(future, ba);
        long t = TimeBooleanUAppender.req.send(future, ba);
        System.out.println("list size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.count * ba.repeat / t;
            System.out.println("Updates per second = " + ips);
        }

        jaBundleContext.stop(0);
    }
}
