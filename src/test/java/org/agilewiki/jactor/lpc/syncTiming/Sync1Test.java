package org.agilewiki.jactor.lpc.syncTiming;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jactor.old.JAMailboxFactory;
import org.agilewiki.jactor.old.Mailbox;
import org.agilewiki.jactor.old.MailboxFactory;

public class Sync1Test extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            JAFuture future = new JAFuture();
            Mailbox mailbox = mailboxFactory.createMailbox();
            Echo echo = new Echo();
            echo.initialize(mailbox);
            Sender sender = new Sender();
            sender.count = 10;
            sender.echo = echo;
            sender.initialize(mailbox);
            DoSender.req.send(future, sender);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            mailboxFactory.close();
        }
    }
}
