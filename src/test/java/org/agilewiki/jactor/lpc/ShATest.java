package org.agilewiki.jactor.lpc;

import junit.framework.TestCase;
import org.agilewiki.jactor.*;
import org.agilewiki.jactor.old.*;

/**
 * Test code.
 */
public class ShATest extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            Mailbox shared = mailboxFactory.createMailbox();
            A a = new A();
            a.initialize(mailboxFactory.createAsyncMailbox());
            S s1 = new S(a);
            s1.initialize(shared);
            S s2 = new S(s1);
            s2.initialize(shared);
            JAFuture future = new JAFuture();
            System.err.println(SimpleRequest.req.send(future, s2));
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            mailboxFactory.close();
        }
    }

    class S extends SimpleRequestReceiver {
        Actor n;

        S(Actor n) {
            this.n = n;
        }

        @Override
        public void processRequest(SimpleRequest request, RP rp) throws Exception {
            System.err.println("S got request");
            send(n, request, rp);
        }
    }

    class A extends SimpleRequestReceiver {
        @Override
        public void processRequest(SimpleRequest request, RP rp) throws Exception {
            System.err.println("A got request");
            rp.processResponse(request);
        }
    }
}
