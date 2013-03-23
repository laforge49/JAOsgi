package org.agilewiki.jactor.counterTest;

import org.agilewiki.jactor.*;
import org.agilewiki.jactor.old.JAIterator;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.simpleMachine.ObjectFunc;
import org.agilewiki.jactor.simpleMachine.SimpleMachine;
import org.agilewiki.jactor.simpleMachine._Operation;

/**
 * Test code.
 */
final public class Driver extends SimpleRequestReceiver {
    SMBuilder smb = new SMBuilder();

    public void initialize(Mailbox mailbox, final CounterActor counterActor, final long runs)
            throws Exception {
        super.initialize(mailbox);
        smb.add(new _Operation() {
            @Override
            public void call(final SimpleMachine sm, final ResponseProcessor rp1) throws Exception {
                JAIterator it = new JAIterator() {
                    long i = 0;

                    @Override
                    protected void process(ResponseProcessor rp1) throws Exception {
                        if (i == runs) rp1.processResponse(this);
                        else {
                            i += 1;
                            AddCount addCount = new AddCount();
                            addCount.number = 100L;
                            addCount.send(Driver.this, counterActor, rp1);
                        }
                    }
                };
                it.iterate(new ResponseProcessor() {
                    @Override
                    public void processResponse(Object response) throws Exception {
                        rp1.processResponse(null);
                    }
                });
            }
        });
        smb._send(counterActor, new GetAndReset(), "count");
        smb._return(new ObjectFunc() {
            @Override
            public Object get(SimpleMachine sm) {
                return sm.get("count");
            }
        });
    }

    @Override
    public void processRequest(SimpleRequest request,
                               final ResponseProcessor rp)
            throws Exception {
        smb.call(rp);
    }
}
