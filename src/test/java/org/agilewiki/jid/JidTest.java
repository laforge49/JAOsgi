package org.agilewiki.jid;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;

public class JidTest extends TestCase {
    public void test1() throws Exception {
        System.err.println("\nTest 1");
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Jid a = (Jid) factoryLocator.newJid(JidFactories.JID_TYPE);
            int l = GetSerializedLength.req.send(future, a);
            System.err.println(l);
            assertEquals(l, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test3() throws Exception {
        System.err.println("\nTest 3");
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Jid a = (Jid) factoryLocator.newJid(JidFactories.JID_TYPE);
            int l = GetSerializedLength.req.send(future, a);
            AppendableBytes appendableBytes = new AppendableBytes(l);
            (new Save(appendableBytes)).send(future, a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test4() throws Exception {
        System.err.println("\nTest 4");
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Jid a = (Jid) factoryLocator.newJid(JidFactories.JID_TYPE);
            byte[] bytes = GetSerializedBytes.req.send(future, a);
            int l = bytes.length;
            System.err.println(l);
            assertEquals(l, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test5() throws Exception {
        System.err.println("\nTest 5");
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Jid a = (Jid) factoryLocator.newJid(JidFactories.JID_TYPE);
            a.load(new ReadableBytes(new byte[0], 0));
            int l = GetSerializedLength.req.send(future, a);
            System.err.println(l);
            assertEquals(l, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test6() throws Exception {
        System.err.println("\nTest 6");
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Jid jid1 = (Jid) factoryLocator.newJid(JidFactories.JID_TYPE);
            jid1.load(new ReadableBytes(new byte[0], 0));
            Jid jid2 = (Jid) (new CopyJID(factoryLocator.getMailbox())).send(future, jid1);
            int l = GetSerializedLength.req.send(future, jid2);
            System.err.println(l);
            assertEquals(l, 0);
            boolean eq = (new IsJidEqual(jid2)).send(future, jid1);
            assertTrue(eq);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    public void test7() throws Exception {
        System.err.println("\nTest 7");
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            JAFuture future = new JAFuture();
            Jid a = (Jid) factoryLocator.newJid(JidFactories.JID_TYPE);
            Jid b = (Jid) future.send(a, new ResolvePathname(""));
            assertEquals(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
