package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAString extends IncDes {
    Request<String> getStringReq();

    Request<Void> setStringReq(final String _v);

    Request<Boolean> makeStringReq(final String _v);
}
