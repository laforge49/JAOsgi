package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jaosgi.JAFactoryLocator;
import org.agilewiki.jaosgi.JidFactories;
import org.agilewiki.jid.ReadableBytes;
import org.agilewiki.jid.collection.vlenc.map.IntegerMapJid;
import org.agilewiki.jid.scalar.flens.integer.IntegerJid;

public class MapUAppender extends JLPCActor {
    public int count;
    public int repeat;
    public IntegerMapJid map;

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
            IntegerMapJid blj = (IntegerMapJid) JAFactoryLocator.
                    newActor(this, JidFactories.INTEGER_INTEGER_MAP_JID_TYPE, getMailbox(), getParent());
            blj.load(rb);
            IntegerJid sj = (IntegerJid) blj.kGet(j);
            sj.setValue(42);
            bytes = blj.getSerializedBytes();
            j += 1;
        }
        long t1 = System.currentTimeMillis();
        return t1 - t0;
    }
}
