package org.agilewiki.jid.timings.list;

import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.incdes.ReadableBytes;
import org.agilewiki.jid.collection.vlenc.ListJid;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

public class BooleanDAppender extends JLPCActor {
    public int count;
    public int repeat;
    public ListJid list;

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
            ListJid blj = (ListJid) JAFactoryLocator.
                    newJid(this, JidFactories.STRING_LIST_JID_TYPE, getMailbox(), getParent());
            blj.load(rb);
            j += 1;
        }
        long t1 = System.currentTimeMillis();
        return t1 - t0;
    }
}
