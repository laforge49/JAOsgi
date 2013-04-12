package org.agilewiki.jid.timings.list;

import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.ReadableBytes;
import org.agilewiki.incdes.impl.collection.vlenc.map.IntegerSMap;
import org.agilewiki.incdes.impl.scalar.flens.PAIntegerImpl;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;

public class MapUAppender extends JLPCActor {
    public int count;
    public int repeat;
    public IntegerSMap map;

    protected long time() throws Exception {
        int i = 0;
        while (i < count) {
            map.kMake(i);
            i += 1;
        }
        byte[] bytes = map.getSerializedBytes();
        map.empty();
        long t0 = System.currentTimeMillis();
        int j = 0;
        while (j < repeat) {
            ReadableBytes rb = new ReadableBytes(bytes, 0);
            IntegerSMap blj = (IntegerSMap) FactoryLocatorImpl.
                    newJid(this, IncDesFactories.INTEGER_PAINTEGER_MAP, getMailbox(), getParent());
            blj.load(rb);
            PAIntegerImpl sj = (PAIntegerImpl) blj.kGet(j);
            sj.setValue(42);
            bytes = blj.getSerializedBytes();
            j += 1;
        }
        long t1 = System.currentTimeMillis();
        return t1 - t0;
    }
}
