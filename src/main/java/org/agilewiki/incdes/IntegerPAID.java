package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface IntegerPAID extends PAID {
    Request<Integer> getIntegerReq();

    Request<Void> setIntegerReq(final Integer _v);
}
