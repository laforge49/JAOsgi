package org.agilewiki.paid;

import org.agilewiki.pactor.Request;

public interface DoublePAID extends PAID {
    Request<Double> getDoubleReq();
    Request<Void> setDoubleReq(final Double _v);
}
