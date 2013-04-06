package org.agilewiki.paid;

import org.agilewiki.pactor.Request;

public interface LongPAID extends PAID {
    Request<Long> getLongReq();

    Request<Void> setLongReq(final Long _v);
}
