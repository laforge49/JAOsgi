package org.agilewiki.jid.collection.vlenc;

import junit.framework.TestCase;
import org.agilewiki.incdes.PAFactories;
import org.agilewiki.incdes.PABundleContext;
import org.agilewiki.incdes.impl.collection.vlenc.BList;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class BListTimingsTest extends TestCase {
    public void test() throws Exception {

        int s = 1000;
        int r = 1000;

        //list size = 1,000
        //repetitions = 1,000
        //total run time (milliseconds) =  14
        //time per update (microseconds) = 16

        //list size = 10,000
        //repetitions = 10,000
        //total run time (milliseconds) = 379
        //time per update (microseconds) = 37

        //list size = 100,000
        //repetitions = 10,000
        //total run time (milliseconds) = 2595
        //time per update (microseconds) = 259

        //list size = 1,000,000
        //repetitions = 10,000
        //total run time (milliseconds) = 29379
        //time per update (microseconds) = 2937

        FactoryLocatorImpl factoryLocator = PAFactories.createFactoryLocator(1);
        PABundleContext jaBundleContext = PABundleContext.get(factoryLocator);
        try {
            BList intList1 = (BList) factoryLocator.newJid(PAFactories.INTEGER_BLIST_JID_TYPE);
            Mailbox mailbox = factoryLocator.getMailbox();
            int i = 0;
            while (i < s) {
                intList1.iAdd(-1);
                PAIntegerImpl ij0 = (PAIntegerImpl) intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            intList1.getSerializedBytes();
            long t0 = System.currentTimeMillis();
            int j = 0;
            while (j < r) {
                BList intList2 = (BList) intList1.copyJID(mailbox);
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
