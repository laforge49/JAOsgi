package org.agilewiki.jactor.iteratorTest.factorialTest;

import junit.framework.TestCase;
import org.agilewiki.jactor.SimpleRequest;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jactor.old.JAMailboxFactory;
import org.agilewiki.jactor.old.MailboxFactory;

/**
 * Test code.
 */
public class FactorialTest extends TestCase {
    public void testFactorial() throws Exception {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            Factorial factorial = new Factorial();
            factorial.initialize(mailboxFactory.createMailbox());
            JAFuture future = new JAFuture();
            SimpleRequest.req.send(future, factorial);
        } finally {
            mailboxFactory.close();
        }
    }
}
