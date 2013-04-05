package org.agilewiki.paid;

import org.agilewiki.pactor.Request;

public interface StringPAID extends PAID {
    Request<String> getStringReq();
    Request<Void> setStringReq(final String _v);
    Request<Boolean> makeStringReq(final String _v);
}
