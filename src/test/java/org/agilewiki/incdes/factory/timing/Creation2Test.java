package org.agilewiki.incdes.factory.timing;

import junit.framework.TestCase;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.factory.timing.A;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.MailboxFactory;

public class Creation2Test extends TestCase {
    FactoryLocator factoryLocator;

    public void test() throws Exception {

        long c = 10000000;

        //System.out.println("####################################################");
        //long c = 10,000,000;
        //iterations per second = 10,672,358

        factoryLocator = IncDesFactories.createFactoryLocator();
        Context jaBundleContext = Context.get(factoryLocator);
        try {
            A.registerFactory(factoryLocator);
            MailboxFactory mailboxFactory = jaBundleContext.getMailboxFactory();
            Mailbox m = mailboxFactory.createMailbox();
            loop(c, m);
            loop(c, m);
            long t0 = System.currentTimeMillis();
            loop(c, m);
            long t1 = System.currentTimeMillis();
            long d = t1 - t0;
            System.out.println(d);
            if (d > 0)
                System.out.println(1000 * c / d);
        } finally {
            jaBundleContext.stop(0);
        }
    }

    void loop(long c, Mailbox m)
            throws Exception {
        int i = 0;
        while (i < c) {
            assertNotNull(A.create(factoryLocator, m, null));
            i += 1;
        }
    }
}
