package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface Box extends IncDes {
    Request<PASerializable> getIncDesReq();

    PASerializable getValue()
            throws Exception;

    Request<Void> clearReq();

    void clear() throws Exception;

    Request<Void> setIncDesReq(final String _v);

    void setValue(final String _jidType)
            throws Exception;

    Request<Void> setIncDesReq(final String _v, final byte[] _bytes);

    void setValue(final String _jidType, final byte[] _bytes)
            throws Exception;

    Request<Boolean> makeIncDesReq(final String _v);

    Boolean makeValue(final String _jidType)
            throws Exception;

    Request<Boolean> makeIncDesReq(final String _v, final byte[] _bytes);

    Boolean makeValue(final String _jidType, final byte[] _bytes)
            throws Exception;
}
