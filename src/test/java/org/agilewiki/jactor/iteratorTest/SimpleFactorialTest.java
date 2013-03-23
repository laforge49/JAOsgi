package org.agilewiki.jactor.iteratorTest;

import junit.framework.TestCase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.JAIterator;

/**
 * Test code.
 */
public class SimpleFactorialTest extends TestCase {
    public void testFactorial() throws Exception {
        final int max = 5;
        ResponseProcessor printResult = new ResponseProcessor() {
            public void processResponse(Object rsp) {
                System.out.println(rsp);
            }
        };

        (new JAIterator() {
            int i;
            int r = 1;

            public void process(ResponseProcessor rp) throws Exception {
                if (i >= max) rp.processResponse(new Integer(r));
                else {
                    i += 1;
                    r = r * i;
                    rp.processResponse(null);
                }
            }
        }).iterate(printResult);
    }
}
