package org.agilewiki.jactor.lpc.timingTest;

import junit.framework.TestCase;
import org.agilewiki.jactor.*;
import org.agilewiki.jactor.old.*;

/**
 * Test code.
 */
public class MailboxTest extends TestCase {
    public void testTiming() {
        int c = 1;
        int b = 1;
        int p = 1;
        int t = 1;

        //System.out.println("####################################################");
        //int c = 1000;
        //int b = 1000;
        //int p = 1000;
        //int t = 8;

        //burst size of 100
        //1000 parallel runs of 2,000,000 messages each.
        //2,000,000,000 messages sent with 8 threads.
        //msgs per sec = 151,987,233

        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(t);
        try {
            Actor[] senders = new Actor[p];
            int i = 0;
            while (i < p) {
                Mailbox echoMailbox = mailboxFactory.createMailbox();
                Echo echo = new Echo();
                echo.initialize(echoMailbox);
                echo.setInitialBufferCapacity(b + 10);
                Mailbox senderMailbox = mailboxFactory.createAsyncMailbox();
                if (b == 1) {
                    Sender1 s = new Sender1(echo, c, b);
                    s.initialize(senderMailbox);
                    senders[i] = s;
                } else {
                    Sender s = new Sender(echo, c, b);
                    s.initialize(senderMailbox);
                    senders[i] = s;
                }
                senders[i].setInitialBufferCapacity(b + 10);
                i += 1;
            }
            JAParallel parallel = new JAParallel();
            parallel.initialize(mailboxFactory.createMailbox());
            parallel.actors = senders;
            JAFuture future = new JAFuture();
            RealRequest.req.send(future, parallel);
            RealRequest.req.send(future, parallel);
            long t0 = System.currentTimeMillis();
            RealRequest.req.send(future, parallel);
            long t1 = System.currentTimeMillis();
            SimpleRequest.req.send(future, parallel);
            long t2 = System.currentTimeMillis();
            System.out.println("null test time " + (t2 - t1));
            System.out.println("" + p + " parallel runs of " + (2L * c * b) + " messages each.");
            System.out.println("" + (2L * c * b * p) + " messages sent with " + t + " threads.");
            if (t1 != t0 && t1 - t0 - t2 + t1 > 0) {
                System.out.println("msgs per sec = " + ((2L * c * b * p) * 1000L / (t1 - t0)));
                System.out.println("adjusted msgs per sec = " + ((2L * c * b * p) * 1000L / (t1 - t0 - t2 + t1)));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            mailboxFactory.close();
        }
    }
}
