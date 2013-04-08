package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAString extends IncDes {
    Request<String> getStringReq();

    String getValue();

    Request<Void> setStringReq(final String _v);

    void setValue(final String _v) throws Exception;

    Request<Boolean> makeStringReq(final String _v);

    Boolean makeValue(final String _v) throws Exception;
}
