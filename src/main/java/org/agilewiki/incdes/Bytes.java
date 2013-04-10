package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface Bytes extends IncDes {
    Request<byte[]> getBytesReq();

    byte[] getValue();

    Request<Void> clearReq();

    void clear() throws Exception;

    Request<Void> setBytesReq(final byte[] _v);

    void setValue(final byte[] _v) throws Exception;

    Request<Boolean> makeBytesReq(final byte[] _v);

    Boolean makeValue(final byte[] v) throws Exception;
}
