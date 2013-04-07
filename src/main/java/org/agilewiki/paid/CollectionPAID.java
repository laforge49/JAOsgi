package org.agilewiki.paid;

import org.agilewiki.pactor.Request;

public interface CollectionPAID<ENTRY_TYPE extends PAID> extends PAID {
    Request<Integer> sizeReq();

    Request<ENTRY_TYPE> iGetReq(final int _i);

    Request<Void> iSetReq(final int _i, final byte[] _bytes);
}
