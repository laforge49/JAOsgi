package org.agilewiki.paid;

import org.agilewiki.pactor.Request;

public interface FloatPAID extends PAID {
    Request<Float> getFloatReq();
    Request<Void> setFloatReq(final Float _v);
}
