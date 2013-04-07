package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PABoolean extends PAIncDes {
    Request<Boolean> getBooleanReq();

    Request<Void> setBooleanReq(final Boolean _v);
}
