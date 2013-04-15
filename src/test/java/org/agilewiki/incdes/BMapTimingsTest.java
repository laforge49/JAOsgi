package org.agilewiki.incdes;

import junit.framework.TestCase;
import org.agilewiki.pactor.Mailbox;

public class BMapTimingsTest extends TestCase {
    public void test1() throws Exception {

        int s = 1000;
        int r = 1000;

        //map size = 1000
        //repetitions = 1000
        //total run time (milliseconds) =  22
        //time per update (microseconds) = 22

        //map size = 10000
        //repetitions = 10000
        //total run time (milliseconds) = 496
        //time per update (microseconds) = 49

        //map size = 100000
        //repetitions = 10000
        //total run time (milliseconds) = 4724
        //time per update (microseconds) = 472

        //map size = 1000000
        //repetitions = 1000
        //total run time (milliseconds) =  10905
        //time per update (microseconds) = 10905

        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        try {
            PAMap<Integer, PAInteger> m1 = (PAMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < s) {
                m1.kMake(i);
                PAInteger ij0 = m1.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            m1.getSerializedBytes();
            int j = 0;
            i = s / 2;
            Mailbox mailbox = factoryLocator.getMailboxFactory().createMailbox();
            long t0 = System.currentTimeMillis();
            while (j < r) {
                PAMap<Integer, PAInteger> m2 = (PAMap) m1.copy(mailbox);
                PAInteger ij0 = m1.kGet(i);
                ij0.setValue(-i);
                m2.getSerializedBytes();
                j += 1;
            }
            long t1 = System.currentTimeMillis();
            System.out.println("map size = " + s);
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
