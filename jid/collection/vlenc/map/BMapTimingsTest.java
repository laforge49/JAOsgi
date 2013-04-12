package org.agilewiki.jid.collection.vlenc.map;

import junit.framework.TestCase;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.impl.collection.vlenc.map.BMap;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class BMapTimingsTest extends TestCase {
    public void test1() throws Exception {

        int s = 1000;
        int r = 1000;

        //map size = 1000
        //repetitions = 1000
        //total run time (milliseconds) =  38
        //time per update (microseconds) = 38

        //map size = 10000
        //repetitions = 10000
        //total run time (milliseconds) = 568
        //time per update (microseconds) = 56

        //map size = 100000
        //repetitions = 10000
        //total run time (milliseconds) = 5123
        //time per update (microseconds) = 512

        //map size = 1000000
        //repetitions = 1000
        //total run time (milliseconds) =  7277
        //time per update (microseconds) = 7277

        FactoryLocatorImpl factoryLocator = IncDesFactories.createFactoryLocator(1);
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            BMap<Integer, PAIntegerImpl> m1 = (BMap) factoryLocator.
                    newJid(IncDesFactories.INTEGER_PAINTEGER_BMAP);
            int i = 0;
            while (i < s) {
                m1.kMake(i);
                PAIntegerImpl ij0 = m1.kGet(i);
                ij0.setValue(i);
                i += 1;
            }
            m1.getSerializedBytes();
            long t0 = System.currentTimeMillis();
            int j = 0;
            i = s / 2;
            while (j < r) {
                BMap m2 = (BMap) m1.copy(factoryLocator.getMailbox());
                PAIntegerImpl ij0 = m1.kGet(i);
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
            jaBundleContext.stop(0);
        }
    }
}
