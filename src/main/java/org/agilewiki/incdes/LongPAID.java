package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface LongPAID extends PAID {
    Request<Long> getLongReq();

    Request<Void> setLongReq(final Long _v);
}
