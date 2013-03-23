package org.agilewiki.jactor.simpleMachine;

import junit.framework.TestCase;
import org.agilewiki.jactor.*;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jactor.old.JAMailboxFactory;
import org.agilewiki.jactor.old.MailboxFactory;
import org.agilewiki.jactor.ResponseProcessor;

/**
 * Test code.
 */
public class ReturnVTest extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            JAFuture future = new JAFuture();
            ReturnV1 actor1 = new ReturnV1();
            actor1.initialize(mailboxFactory.createMailbox());
            System.out.println(SimpleRequest.req.send(future, actor1));
            ReturnV2 actor2 = new ReturnV2();
            actor2.initialize(mailboxFactory.createMailbox());
            System.out.println(SimpleRequest.req.send(future, actor2));
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            mailboxFactory.close();
        }
    }

    class ReturnV1 extends SimpleRequestReceiver {
        @Override
        public void processRequest(SimpleRequest unwrappedRequest, ResponseProcessor rp) throws Exception {
            SMBuilder smb = new SMBuilder();
            String rv = "Hello world!";
            smb._return(rv);
            rv = null;
            smb.call(rp);
            //Output:
            //Hello world!
        }
    }

    class ReturnV2 extends SimpleRequestReceiver {
        @Override
        public void processRequest(SimpleRequest unwrappedRequest, ResponseProcessor rp) throws Exception {
            SMBuilder smb = new SMBuilder();
            smb._return(null);
            smb.call(rp);
            //Output:
            //null
        }
    }
}
