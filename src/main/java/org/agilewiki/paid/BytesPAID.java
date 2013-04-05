package org.agilewiki.paid;

import org.agilewiki.pactor.Request;

public interface BytesPAID extends PAID {
    Request<byte[]> getBytesReq();
    Request<Void> setBytesReq(final byte[] _v);
    Request<Boolean> makeBytesReq(final byte[] _v);
}
