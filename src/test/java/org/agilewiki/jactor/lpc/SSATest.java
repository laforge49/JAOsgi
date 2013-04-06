package org.agilewiki.jactor.lpc;

import junit.framework.TestCase;
import org.agilewiki.jactor.SimpleRequest;
import org.agilewiki.jactor.SimpleRequestReceiver;
import org.agilewiki.jactor.old.*;

/**
 * Test code.
 */
public class SSATest extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            A a = new A();
            a.initialize(mailboxFactory.createAsyncMailbox());
            S s1 = new S(a);
            s1.initialize(mailboxFactory.createMailbox());
            S s2 = new S(s1);
            s2.initialize(mailboxFactory.createMailbox());
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
        public void processRequest(SimpleRequest request, final ResponseProcessor rp) throws Exception {
            System.err.println("S got request");
            send(n, request, new ResponseProcessor() {
                @Override
                public void processResponse(Object response) throws Exception {
                    System.err.println("S got response");
                    rp.processResponse(response);
                }
            });
        }
    }

    class A extends SimpleRequestReceiver {
        @Override
        public void processRequest(SimpleRequest request, ResponseProcessor rp) throws Exception {
            System.err.println("A got request");
            rp.processResponse(request);
        }
    }
}
