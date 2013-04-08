package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PABytes extends PAIncDes {
    Request<byte[]> getBytesReq();

    Request<Void> setBytesReq(final byte[] _v);

    Request<Boolean> makeBytesReq(final byte[] _v);
}