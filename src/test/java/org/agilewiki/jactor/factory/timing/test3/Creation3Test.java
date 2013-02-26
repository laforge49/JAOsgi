package org.agilewiki.jactor.factory.timing.test3;

import junit.framework.TestCase;
import org.agilewiki.jactor.factory.timing.A;
import org.agilewiki.jaosgi.FactoryLocator;
import org.agilewiki.jaosgi.JABCNoOsgiImpl;
import org.agilewiki.jaosgi.JABundleContext;

public class Creation3Test extends TestCase {
    public void test() throws Exception {

        long c = 1;

        //System.out.println("####################################################");
        //c = 10000000;
        //iterations per second = 49,261,083

        FactoryLocator factoryLocator = JABCNoOsgiImpl.createFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.getJABundleContext(factoryLocator);
        try {
            factoryLocator.defineActorType("A", A.class);
            loop(c, factoryLocator);
            loop(c, factoryLocator);
            long t0 = System.currentTimeMillis();
            loop(c, factoryLocator);
            long t1 = System.currentTimeMillis();
            long d = t1 - t0;
            if (d > 0)
                System.out.println(1000 * c / d);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jaBundleContext.stop(0);
        }
    }

    void loop(long c, FactoryLocator factoryLocator) throws Exception {
        int i = 0;
        while (i < c) {
            factoryLocator.newActor("A");
            i += 1;
        }
    }
}
