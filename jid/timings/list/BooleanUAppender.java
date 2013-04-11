package org.agilewiki.jid.timings.list;

import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.ReadableBytes;
import org.agilewiki.incdes.impl.collection.vlenc.SList;
import org.agilewiki.incdes.impl.scalar.flens.PABooleanImpl;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class BooleanUAppender extends JLPCActor {
    public int count;
    public int repeat;
    public SList list;

    protected long time() throws Exception {
        int i = 0;
        while (i < count) {
            list.iAdd(-1);
            i += 1;
        }
        byte[] bytes = list.getSerializedBytes();
        list.empty();
        long t0 = System.currentTimeMillis();
        int j = 0;

        while (j < repeat) {
            ReadableBytes rb = new ReadableBytes(bytes, 0);
            SList blj = (SList) FactoryLocatorImpl.
                    newJid(this, IncDesFactories.PABOOLEAN_LIST, getMailbox(), getParent());
            blj.load(rb);
            PABooleanImpl bj = (PABooleanImpl) blj.iGet(j);
            bj.setValue(true);
            bytes = blj.getSerializedBytes();
            j += 1;
        }
        long t1 = System.currentTimeMillis();
        return t1 - t0;
    }
}
