package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAInteger extends PAIncDes {
    Request<Integer> getIntegerReq();

    Request<Void> setIntegerReq(final Integer _v);
}
