package org.agilewiki.jactor.basics;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jactor.old.JAMailboxFactory;
import org.agilewiki.jactor.old.Mailbox;
import org.agilewiki.jactor.old.MailboxFactory;

/**
 * Test code.
 */
public class Test2a extends TestCase {
    public void test() throws Exception {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(10);
        Mailbox mailbox = mailboxFactory.createMailbox();
        Actor1 actor1 = new Actor1();
        actor1.initialize(mailbox);
        Actor2 actor2 = new Actor2();
        actor2.initialize(mailbox, actor1);
        JAFuture future = new JAFuture();
        String result = Hi1.req.send(future, actor2);
        assertEquals("Hello world!", result);
        mailboxFactory.close();
    }
}
