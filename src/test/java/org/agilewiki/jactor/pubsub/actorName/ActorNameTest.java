package org.agilewiki.jactor.pubsub.actorName;

import junit.framework.TestCase;
import org.agilewiki.jactor.old.JAMailboxFactory;
import org.agilewiki.jactor.old.MailboxFactory;

/**
 * Test code.
 */
public class ActorNameTest extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            JActorName a = new JActorName();
            a.initialize(mailboxFactory.createMailbox());
            a.setActorName("foo");
            String nm = a.getActorName();
            assertEquals("foo", nm);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            mailboxFactory.close();
        }
    }
}
