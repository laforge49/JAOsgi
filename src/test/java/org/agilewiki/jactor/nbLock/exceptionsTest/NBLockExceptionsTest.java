package org.agilewiki.jactor.nbLock.exceptionsTest;

import junit.framework.TestCase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.lpc.TargetActor;
import org.agilewiki.jactor.nbLock.JANBLock;
import org.agilewiki.jactor.old.*;

/**
 * Test code.
 */
public class NBLockExceptionsTest extends TestCase {
    public void test() {
        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(10);
        try {
            JAFuture future = new JAFuture();
            JANBLock nblock = new JANBLock();
            nblock.initialize(mailboxFactory.createAsyncMailbox());
            Driver driver = new Driver();
            driver.initialize(mailboxFactory.createAsyncMailbox(), nblock);
            (new DoItEx()).send(future, driver);
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
interface Does extends TargetActor {
    public void does(ResponseProcessor rp) throws Exception;
}

/**
 * Test code.
 */
class DoItEx extends RequestBase<Object, Does> {

    /**
     * Returns true when targetActor is an instanceof TARGET_TYPE
     *
     * @param targetActor The actor to be called.
     * @return True when targetActor is an instanceof TARGET_TYPE.
     */
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof Does;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        ((Does) targetActor).does(rp);
    }
}
