package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PALong extends IncDes {
    Request<Long> getLongReq();

    Request<Void> setLongReq(final Long _v);
}
