package org.agilewiki.jid;

import junit.framework.TestCase;
import org.agilewiki.incdes.AppendableBytes;
import org.agilewiki.incdes.IncDes;
import org.agilewiki.incdes.ReadableBytes;
import org.agilewiki.incdes.impl.IncDesImpl;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;

public class JidTest extends TestCase {
    public void test1() throws Exception {
        System.err.println("\nTest 1");
        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
            IncDes a = factoryLocator.newJid(JidFactories.JID_TYPE);
            int l = a.getSerializedLengthReq().call();
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
            IncDes a = factoryLocator.newJid(JidFactories.JID_TYPE);
            int l = a.getSerializedLengthReq().call();
            AppendableBytes appendableBytes = new AppendableBytes(l);
            a.save(appendableBytes);
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
            IncDesImpl a = (IncDesImpl) factoryLocator.newJid(JidFactories.JID_TYPE);
            byte[] bytes = a.getSerializedBytesReq().call();
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
            IncDesImpl a = (IncDesImpl) factoryLocator.newJid(JidFactories.JID_TYPE);
            a.load(new ReadableBytes(new byte[0], 0));
            int l = a.getSerializedLengthReq().call();
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
            IncDes jid1 = factoryLocator.newJid(JidFactories.JID_TYPE);
            jid1.load(new ReadableBytes(new byte[0], 0));
            IncDes jid2 = jid1.copyJIDReq(factoryLocator.getMailbox()).call();
            int l = jid2.getSerializedLengthReq().call();
            System.err.println(l);
            assertEquals(l, 0);
            boolean eq = jid1.isJidEqualReq(jid2).call();
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
            IncDes a = factoryLocator.newJid(JidFactories.JID_TYPE);
            IncDes b = a.resolvePathnameReq("").call();
            assertEquals(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
