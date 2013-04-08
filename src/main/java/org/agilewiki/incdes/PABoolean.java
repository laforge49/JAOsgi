package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PABoolean extends PAIncDes {
    Request<Boolean> getBooleanReq();

    Boolean getValue();

    Request<Void> setBooleanReq(final Boolean _v);

    void setValue(final Boolean v) throws Exception;
}
