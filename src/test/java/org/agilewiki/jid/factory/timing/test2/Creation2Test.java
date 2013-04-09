package org.agilewiki.jid.factory.timing.test2;

import junit.framework.TestCase;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.factory.timing.A;
import org.agilewiki.jid.jaosgi.JABundleContext;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.MailboxFactory;

public class Creation2Test extends TestCase {
    public void test() throws Exception {

        long c = 1;

        //System.out.println("####################################################");
        //long c = 1000000000;
        //iterations per second = 2,801,120,448

        JAFactoryLocator factoryLocator = JidFactories.createNoOsgiFactoryLocator(1);
        JABundleContext jaBundleContext = JABundleContext.get(factoryLocator);
        try {
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
            (new A()).initialize(m);
            i += 1;
        }
    }
}
