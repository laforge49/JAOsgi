package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PADouble extends IncDes {
    Request<Double> getDoubleReq();

    Request<Void> setDoubleReq(final Double _v);
}
