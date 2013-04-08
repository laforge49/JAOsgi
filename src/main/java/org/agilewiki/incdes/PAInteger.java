package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAInteger extends IncDes {
    Request<Integer> getIntegerReq();

    Request<Void> setIntegerReq(final Integer _v);
}
