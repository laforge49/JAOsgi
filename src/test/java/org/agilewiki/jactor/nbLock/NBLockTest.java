package org.agilewiki.jactor.nbLock;

import junit.framework.TestCase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.*;

/**
 * Test code.
 */
public class NBLockTest extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(10);
        try {
            JAFuture future = new JAFuture();
            Driver driver = new Driver();
            driver.initialize(mailboxFactory.createMailbox());
            (new DoIt()).send(future, driver);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            mailboxFactory.close();
        }
    }
}

/**
 * Test code.
 */
class DoIt extends RequestBase<Object, Driver> {

    /**
     * Returns true when targetActor is an instanceof TARGET_TYPE
     *
     * @param targetActor The actor to be called.
     * @return True when targetActor is an instanceof TARGET_TYPE.
     */
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Driver;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((Driver) targetActor).doit(rp);
    }
}
