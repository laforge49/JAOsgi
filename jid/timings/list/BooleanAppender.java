package org.agilewiki.jid.timings.list;

import org.agilewiki.incdes.impl.collection.vlenc.SList;
import org.agilewiki.jactor.lpc.JLPCActor;

public class BooleanAppender extends JLPCActor {
    public int count;
    public int repeat;
    public SList list;

    protected long time() throws Exception {
        long t0 = System.currentTimeMillis();
        int j = 0;
        while (j < repeat) {
            int i = 0;
            while (i < count) {
                list.iAdd(-1);
                i += 1;
            }
            list.empty();
            j += 1;
        }
        long t1 = System.currentTimeMillis();
        return t1 - t0;
    }
}
