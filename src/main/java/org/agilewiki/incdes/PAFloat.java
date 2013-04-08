package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAFloat extends IncDes {
    Request<Float> getFloatReq();

    Request<Void> setFloatReq(final Float _v);
}
