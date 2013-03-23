package org.agilewiki.jactor.lpc;

import junit.framework.TestCase;
import org.agilewiki.jactor.*;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jactor.old.JAMailboxFactory;
import org.agilewiki.jactor.old.MailboxFactory;
import org.agilewiki.jactor.ResponseProcessor;

/**
 * Test code.
 */
public class EventTest extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            EventAReceiver a = new EventAReceiver();
            a.initialize(mailboxFactory.createMailbox());
            JAFuture future = new JAFuture();
            SimpleRequest eventA = new SimpleRequest();
            eventA.sendEvent(a);
            eventA.send(future, a);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            mailboxFactory.close();
        }
    }
}

class EventAReceiver extends SimpleRequestReceiver {
    public void processRequest(SimpleRequest request, ResponseProcessor rp) throws Exception {
        System.err.println("A got request");
        rp.processResponse(request);
    }
}
