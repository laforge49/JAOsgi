package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PADouble extends PAIncDes {
    Request<Double> getDoubleReq();

    Request<Void> setDoubleReq(final Double _v);
}
