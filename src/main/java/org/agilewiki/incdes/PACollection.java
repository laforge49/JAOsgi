package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PACollection<ENTRY_TYPE extends PAIncDes> extends PAIncDes {
    Request<Integer> sizeReq();

    Request<ENTRY_TYPE> iGetReq(final int _i);

    Request<Void> iSetReq(final int _i, final byte[] _bytes);
}
