package org.agilewiki.jid.collection.vlenc;

import junit.framework.TestCase;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;

public class BListTimingsTest extends TestCase {
    public void test() throws Exception {

        int s = 1000;
        int r = 1000;

        //list size = 10,000
        //repetitions = 10,000
        //total run time (milliseconds) = 359
        //time per update (microseconds) = 35

        //list size = 100,000
        //repetitions = 10,000
        //total run time (milliseconds) = 2394
        //time per update (microseconds) = 239

        //list size = 1,000,000
        //repetitions = 1,000
        //total run time (milliseconds) = 2927
        //time per update (microseconds) = 2927

        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            BListJid intList1 = (BListJid) factoryLocator.newJid(JidFactories.INTEGER_BLIST_JID_TYPE);
            Mailbox mailbox = factoryLocator.getMailbox();
            int i = 0;
            while (i < s) {
                intList1.iAdd(-1);
                IntegerJid ij0 = (IntegerJid) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            intList1.getSerializedBytes();
            long t0 = System.currentTimeMillis();
            int j = 0;
            while (j < r) {
                BListJid intList2 = (BListJid) intList1.copyJID(mailbox);
                intList1.iAdd(s / 2);
                intList2.getSerializedBytes();
                j += 1;
            }
            long t1 = System.currentTimeMillis();
            System.out.println("list size = " + s);
            System.out.println("repetitions = " + r);
            long rt = t1 - t0;
            System.out.println("total run time (milliseconds) = " + rt);
            long tpu = rt * 1000L / r;
            System.out.println("time per update (microseconds) = " + tpu);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }
}
