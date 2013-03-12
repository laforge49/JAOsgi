package org.agilewiki.jid.timings.list;

import junit.framework.TestCase;
import org.agilewiki.jactor.JAFuture;
import org.agilewiki.jid.collection.vlenc.map.IntegerMapJid;
import org.agilewiki.jid.collection.vlenc.map.KMake;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.vlens.actor.GetActor;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;
import org.agilewiki.jid.scalar.vlens.actor.SetActor;

public class BigIntegerIntegerMapTest extends TestCase {
    public void test1() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        SetActor setMap = new SetActor(
                JidFactories.INTEGER_INTEGER_MAP_JID_TYPE);
        setMap.send(future, root);
        IntegerMapJid map = (IntegerMapJid) GetActor.req.send(future, root);

        int i = 0;
        while (i < 10) {
            new KMake(i).send(future, map);
            i += 1;
        }

        jaBundleContext.stop(0);
    }

    public void test2() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        SetActor setMap = new SetActor(
                JidFactories.INTEGER_INTEGER_MAP_JID_TYPE);
        setMap.send(future, root);
        IntegerMapJid map = (IntegerMapJid) GetActor.req.send(future, root);

        MapAppender ba = new MapAppender();
        ba.initialize(factoryLocator.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 5000;
        //ba.repeat = 1000;
        //Appends per second = 5,827,505

        ba.map = map;
        TimeMapAppender.req.send(future, ba);
        long t = TimeMapAppender.req.send(future, ba);
        System.out.println("map size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.count * ba.repeat / t;
            System.out.println("appends per second = " + ips);
        }

        jaBundleContext.stop(0);
    }

    public void test3() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        SetActor setMap = new SetActor(
                JidFactories.INTEGER_INTEGER_MAP_JID_TYPE);
        setMap.send(future, root);
        IntegerMapJid map = (IntegerMapJid) GetActor.req.send(future, root);

        MapAppender ba = new MapAppender();
        ba.initialize(factoryLocator.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 10000;
        //ba.repeat = 1000;
        //Appends per second = 4,533,091

        map.initialCapacity = ba.count;

        ba.map = map;
        TimeMapAppender.req.send(future, ba);
        long t = TimeMapAppender.req.send(future, ba);
        System.out.println("map size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.count * ba.repeat / t;
            System.out.println("appends per second = " + ips);
        }

        jaBundleContext.stop(0);
    }

    public void test4() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        SetActor setMap = new SetActor(
                JidFactories.INTEGER_INTEGER_MAP_JID_TYPE);
        setMap.send(future, root);
        IntegerMapJid map = (IntegerMapJid) GetActor.req.send(future, root);

        MapSAppender ba = new MapSAppender();
        ba.initialize(factoryLocator.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 10000;
        //ba.repeat = 1000;
        //Appends and serializes per second = 5,070,993

        map.initialCapacity = ba.count;

        ba.map = map;
        TimeMapSAppender.req.send(future, ba);
        long t = TimeMapSAppender.req.send(future, ba);
        System.out.println("map size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.count * ba.repeat / t;
            System.out.println("appends and serializes per second = " + ips);
        }

        jaBundleContext.stop(0);
    }

    public void test5() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        SetActor setMap = new SetActor(
                JidFactories.INTEGER_INTEGER_MAP_JID_TYPE);
        setMap.send(future, root);
        IntegerMapJid map = (IntegerMapJid) GetActor.req.send(future, root);

        MapDAppender ba = new MapDAppender();
        ba.initialize(factoryLocator.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 10000;
        //ba.repeat = 10000;
        //Deserializes per second = 33,333,333,333

        map.initialCapacity = ba.count;

        ba.map = map;
        TimeMapDAppender.req.send(future, ba);
        long t = TimeMapDAppender.req.send(future, ba);
        System.out.println("map size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.count * ba.repeat / t;
            System.out.println("Deserializes per second = " + ips);
        }

        jaBundleContext.stop(0);
    }

    public void test6() throws Exception {
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        JAFuture future = new JAFuture();
        RootJid root = (RootJid) factoryLocator.newJid(JidFactories.ROOT_JID_TYPE);
        SetActor setMap = new SetActor(
                JidFactories.INTEGER_INTEGER_MAP_JID_TYPE);
        setMap.send(future, root);
        IntegerMapJid map = (IntegerMapJid) GetActor.req.send(future, root);

        MapUAppender ba = new MapUAppender();
        ba.initialize(factoryLocator.getMailbox(), factoryLocator);

        ba.count = 10;
        ba.repeat = 10;

        //System.out.println("###########################################################");
        //ba.count = 10000;
        //ba.repeat = 1000;
        //Updates per second = 1152

        map.initialCapacity = ba.count;

        ba.map = map;
        TimeMapUAppender.req.send(future, ba);
        long t = TimeMapUAppender.req.send(future, ba);
        System.out.println("map size = " + ba.count);
        System.out.println("repeats = " + ba.repeat);
        if (t > 0) {
            long ips = 1000L * ba.repeat / t;
            System.out.println("Updates per second = " + ips);
        }

        jaBundleContext.stop(0);
    }
}
