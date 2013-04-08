package org.agilewiki.incdes;

import org.agilewiki.jid.Jid;
import org.agilewiki.pactor.Request;

public interface PABox extends IncDes {
    Request<IncDes> getIncDesReq();

    IncDes getValue()
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
