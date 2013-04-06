package org.agilewiki.jactor.lpc;

import junit.framework.TestCase;
import org.agilewiki.jactor.SimpleRequest;
import org.agilewiki.jactor.SimpleRequestReceiver;
import org.agilewiki.jactor.old.*;

/**
 * Test code.
 */
public class ASATest extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            A a = new A();
            a.initialize(mailboxFactory.createAsyncMailbox());
            S s1 = new S(a);
            s1.initialize(mailboxFactory.createMailbox());
            S a2 = new S(s1);
            a2.initialize(mailboxFactory.createAsyncMailbox());
            JAFuture future = new JAFuture();
            System.out.println(SimpleRequest.req.send(future, a2));
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
            request.send(this, SimpleRequestReceiver.get(n), new ResponseProcessor<Object>() {
                @Override
                public void processResponse(Object response) throws Exception {
                    rp.processResponse(null);
                }
            });
        }
    }

    class A extends SimpleRequestReceiver {
        @Override
        public void processRequest(SimpleRequest request, ResponseProcessor rp) throws Exception {
            rp.processResponse(request);
        }
    }
}
