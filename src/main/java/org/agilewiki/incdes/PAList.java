package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAList<ENTRY_TYPE extends PAIncDes> extends PACollection<ENTRY_TYPE> {
    Request<Void> emptyReq();

    Request<Void> iAddReq(final int _i);

    Request<Void> iAddReq(final int _i, final byte[] _bytes);

    Request<Void> iRemoveReq(final int _i);
}
