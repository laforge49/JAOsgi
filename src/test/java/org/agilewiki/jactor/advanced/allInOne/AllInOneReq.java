package org.agilewiki.jactor.advanced.allInOne;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.RequestBase;

abstract public class AllInOneReq extends RequestBase<Object, AllInOne> {
    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof AllInOne;
    }
}
