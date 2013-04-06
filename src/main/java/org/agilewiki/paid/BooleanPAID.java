package org.agilewiki.paid;

import org.agilewiki.pactor.Request;

public interface BooleanPAID extends PAID {
    Request<Boolean> getBooleanReq();

    Request<Void> setBooleanReq(final Boolean _v);
}
