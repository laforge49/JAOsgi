package org.agilewiki.incdes;

import junit.framework.TestCase;
import org.agilewiki.pactor.Mailbox;

public class IncDesTest extends TestCase {
    public void test1() throws Exception {
        System.err.println("\nTest 1");
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            IncDes a = IncDesFactories.createIncDes(factoryLocator, null, null);
            int l = a.getSerializedLengthReq().call();
            System.err.println(l);
            assertEquals(l, 0);
        } finally {
            factoryLocator.close();
        }
    }

    public void test3() throws Exception {
        System.err.println("\nTest 3");
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            IncDes a = IncDesFactories.createIncDes(factoryLocator, null, null);
            int l = a.getSerializedLengthReq().call();
            AppendableBytes appendableBytes = new AppendableBytes(l);
            a.save(appendableBytes);
        } finally {
            factoryLocator.close();
        }
    }

    public void test4() throws Exception {
        System.err.println("\nTest 4");
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            IncDes a = IncDesFactories.createIncDes(factoryLocator, null, null);
            byte[] bytes = a.getSerializedBytesReq().call();
            int l = bytes.length;
            System.err.println(l);
            assertEquals(l, 0);
        } finally {
            factoryLocator.close();
        }
    }

    public void test5() throws Exception {
        System.err.println("\nTest 5");
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            IncDes a = IncDesFactories.createIncDes(factoryLocator, null, null);
            a.load(new ReadableBytes(new byte[0], 0));
            int l = a.getSerializedLengthReq().call();
            System.err.println(l);
            assertEquals(l, 0);
        } finally {
            factoryLocator.close();
        }
    }

    public void test6() throws Exception {
        System.err.println("\nTest 6");
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            IncDes jid1 = IncDesFactories.createIncDes(factoryLocator, null, null);
            jid1.load(new ReadableBytes(new byte[0], 0));
            Mailbox mailbox = factoryLocator.getMailboxFactory().createMailbox();
            IncDes jid2 = jid1.copyReq(mailbox).call();
            int l = jid2.getSerializedLengthReq().call();
            System.err.println(l);
            assertEquals(l, 0);
            boolean eq = jid1.isEqualReq(jid2).call();
            assertTrue(eq);
        } finally {
            factoryLocator.close();
        }
    }

    public void test7() throws Exception {
        System.err.println("\nTest 7");
        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            IncDes a = IncDesFactories.createIncDes(factoryLocator, null, null);
            IncDes b = a.resolvePathnameReq("").call();
            assertEquals(a, b);
        } finally {
            factoryLocator.close();
        }
    }
}
