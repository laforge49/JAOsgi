package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAFloat extends PAIncDes {
    Request<Float> getFloatReq();

    Request<Void> setFloatReq(final Float _v);
}
