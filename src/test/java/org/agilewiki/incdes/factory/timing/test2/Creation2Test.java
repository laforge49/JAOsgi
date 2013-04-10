package org.agilewiki.incdes.factory.timing.test2;

import junit.framework.TestCase;
import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.Context;
import org.agilewiki.incdes.factory.timing.A;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.MailboxFactory;

public class Creation2Test extends TestCase {
    Context jaBundleContext;

    public void test() throws Exception {

        long c = 1;

        //System.out.println("####################################################");
        //long c = 1,000,000,000;
        //iterations per second = 753,012,048

        FactoryLocator factoryLocator = IncDesFactories.createFactoryLocator();
        jaBundleContext = Context.get(factoryLocator);
        try {
            MailboxFactory mailboxFactory = jaBundleContext.getMailboxFactory();
            Mailbox m = mailboxFactory.createMailbox();
            loop(c, m);
            loop(c, m);
            long t0 = System.currentTimeMillis();
            loop(c, m);
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

    void loop(long c, Mailbox m)
            throws Exception {
        int i = 0;
        while (i < c) {
            A.create(jaBundleContext, m, null);
            i += 1;
        }
    }
}
