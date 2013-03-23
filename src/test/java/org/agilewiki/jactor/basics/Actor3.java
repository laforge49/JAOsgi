package org.agilewiki.jactor.basics;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.MailboxFactory;

/**
 * Test code.
 */
public class Actor3 extends Greeter {
    @Override
    public void processRequest(Greet1 request, final ResponseProcessor rp) throws Exception {
        request.send(this, Greeter.get(getParent()), new ResponseProcessor<Object>() {
            @Override
            public void processResponse(Object response) throws Exception {
                MailboxFactory mailboxFactory = getMailbox().getMailboxFactory();
                mailboxFactory.close();
            }
        });
    }
}
