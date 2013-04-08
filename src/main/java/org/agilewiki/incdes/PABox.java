package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PABox extends PAIncDes {
    Request<PAIncDes> getPAIDReq();

    Request<Void> clearReq();

    Request<Void> setPAIDReq(final String _v);

    Request<Void> setPAIDReq(final String _v, final byte[] _bytes);

    Request<Boolean> makePAIDReq(final String _v);

    Request<Boolean> makePAIDReq(final String _v, final byte[] _bytes);
}