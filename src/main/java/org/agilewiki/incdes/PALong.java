package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PALong extends PAIncDes {
    Request<Long> getLongReq();

    Request<Void> setLongReq(final Long _v);
}
