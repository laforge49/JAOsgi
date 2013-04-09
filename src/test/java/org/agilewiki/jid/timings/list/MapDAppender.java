package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.incdes.ReadableBytes;
import org.agilewiki.incdes.impl.collection.vlenc.map.IntegerSMap;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

public class MapDAppender extends JLPCActor {
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
            IntegerSMap blj = (IntegerSMap) JAFactoryLocator.
                    newJid(this, JidFactories.INTEGER_INTEGER_MAP_JID_TYPE, getMailbox(), getParent());
            blj.load(rb);
            j += 1;
        }
        long t1 = System.currentTimeMillis();
        return t1 - t0;
    }
}
