package org.agilewiki.incdes;

import junit.framework.TestCase;

public class JidTest extends TestCase {
    public void test1() throws Exception {
        System.err.println("\nTest 1");
        FactoryLocator factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            IncDes a = factoryLocator.newJid(PAFactories.JID_TYPE);
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
        FactoryLocator factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            IncDes a = factoryLocator.newJid(PAFactories.JID_TYPE);
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
        FactoryLocator factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            IncDes a = factoryLocator.newJid(PAFactories.JID_TYPE);
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
        FactoryLocator factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            IncDes a = factoryLocator.newJid(PAFactories.JID_TYPE);
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
        FactoryLocator factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            IncDes jid1 = factoryLocator.newJid(PAFactories.JID_TYPE);
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
        FactoryLocator factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            IncDes a = factoryLocator.newJid(PAFactories.JID_TYPE);
            IncDes b = a.resolvePathnameReq("").call();
            assertEquals(a, b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
