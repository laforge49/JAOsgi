package org.agilewiki.incdes;

import junit.framework.TestCase;
import org.agilewiki.pactor.Mailbox;

public class BListTimingsTest extends TestCase {
    public void test() throws Exception {

        int s = 1000;
        int r = 1000;

        //list size = 1,000
        //repetitions = 1,000
        //total run time (milliseconds) =  20
        //time per update (microseconds) = 20

        //list size = 10,000
        //repetitions = 10,000
        //total run time (milliseconds) = 433
        //time per update (microseconds) = 43

        //list size = 100,000
        //repetitions = 10,000
        //total run time (milliseconds) = 2382
        //time per update (microseconds) = 238

        //list size = 1,000,000
        //repetitions = 10,000
        //total run time (milliseconds) = 26786
        //time per update (microseconds) = 2678

        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            PAList<PAInteger> intList1 = (PAList) factoryLocator.newJid(IncDesFactories.PAINTEGER_BLIST);
            Mailbox mailbox = factoryLocator.getMailbox();
            int i = 0;
            while (i < s) {
                intList1.iAdd(-1);
                PAInteger ij0 = intList1.iGet(-1);
                ij0.setValue(i);
                i += 1;
            }
            intList1.getSerializedBytes();
            long t0 = System.currentTimeMillis();
            int j = 0;
            while (j < r) {
                PAList<PAInteger> intList2 = (PAList) intList1.copy(mailbox);
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
        } finally {
            factoryLocator.close();
        }
    }
}
