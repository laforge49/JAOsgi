package org.agilewiki.jactor.iteratorTest.factorialTest;

import org.agilewiki.jactor.old.JAIterator;
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.SimpleRequest;
import org.agilewiki.jactor.SimpleRequestReceiver;
import org.agilewiki.jactor.lpc.JLPCActor;

/**
 * Test code.
 */
public class Factorial extends SimpleRequestReceiver {
    @Override
    public void processRequest(SimpleRequest req, final RP rp)
            throws Exception {
        final int max = 5;
        RP printResult = new RP() {
            public void processResponse(Object rsp) throws Exception {
                System.out.println(rsp);
                rp.processResponse(null);
            }
        };
        final Multiplier mp = new Multiplier();
        mp.initialize(getMailbox());
        (new JAIterator() {
            int i;
            int r = 1;

            public void process(RP rp) throws Exception {
                if (i >= max) rp.processResponse(new Integer(r));
                else {
                    i += 1;
                    Multiply m = new Multiply();
                    m.a = r;
                    m.b = i;
                    send(mp, m, new RP() {
                        public void processResponse(Object rsp) throws Exception {
                            r = ((Integer) rsp).intValue();
                        }
                    });
                    rp.processResponse(null);
                }
            }
        }).iterate(printResult);
    }
}
