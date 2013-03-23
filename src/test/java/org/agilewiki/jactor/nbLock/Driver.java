package org.agilewiki.jactor.nbLock;

import org.agilewiki.jactor.ResponseProcessor;

/**
 * Test code.
 */
public class Driver extends JANBLock {
    public void doit(final ResponseProcessor rp) throws Exception {
        final ResponseProcessor<Object> rpc = new ResponseProcessor<Object>() {
            int count = 3;

            @Override
            public void processResponse(Object response) throws Exception {
                count -= 1;
                if (count == 0)
                    rp.processResponse(null);
            }
        };

        Lock.req.send(this, this, new ResponseProcessor<Object>() {
            @Override
            public void processResponse(Object response) throws Exception {
                System.out.println("start 1");
                Thread.sleep(100);
                System.out.println("  end 1");
                Unlock.req.send(Driver.this, Driver.this, rpc);
            }
        });

        Lock.req.send(this, this, new ResponseProcessor<Object>() {
            @Override
            public void processResponse(Object response) throws Exception {
                System.out.println("start 2");
                Thread.sleep(100);
                System.out.println("  end 2");
                Unlock.req.send(Driver.this, Driver.this, rpc);
            }
        });

        Lock.req.send(this, this, new ResponseProcessor<Object>() {
            @Override
            public void processResponse(Object response) throws Exception {
                System.out.println("start 3");
                Thread.sleep(100);
                System.out.println("  end 3");
                Unlock.req.send(Driver.this, Driver.this, rpc);
            }
        });
    }
}
