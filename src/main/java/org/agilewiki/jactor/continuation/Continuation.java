/*
 * Copyright 2011 Bill La Forge
 *
 * This file is part of AgileWiki and is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License (LGPL) as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * or navigate to the following url http://www.gnu.org/licenses/lgpl-2.1.txt
 *
 * Note however that only Scala, Java and JavaScript files are being covered by LGPL.
 * All other files are covered by the Common Public License (CPL).
 * A copy of this license is also included and can be
 * found as well at http://www.opensource.org/licenses/cpl1.0.txt
 */
package org.agilewiki.jactor.continuation;

import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.lpc.JLPCActor;
import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.lpc.TargetActor;

public class Continuation<RESPONSE_TYPE> extends ResponseProcessor<RESPONSE_TYPE> {
    private TargetActor targetActor;
    private ResponseProcessor _rp;

    public Continuation(TargetActor targetActor, ResponseProcessor _rp) {
        this.targetActor = targetActor;
        this._rp = _rp;
    }

    @Override
    public void processResponse(Object rsp) throws Exception {
        (new ContinuationRequest(_rp, rsp)).sendEvent(targetActor);
    }

    public void response(JLPCActor currentActor, Object rsp) throws Exception {
        (new ContinuationRequest(_rp, rsp)).sendEvent(currentActor, targetActor);
    }
}

class ContinuationRequest extends RequestBase<Object, TargetActor> {
    private ResponseProcessor _rp;
    private Object rsp;

    ContinuationRequest(ResponseProcessor _rp, Object rsp) {
        this._rp = _rp;
        this.rsp = rsp;
    }

    @Override
    public boolean isTargetType(Actor targetActor) {
        return true;
    }

    @Override
    public void processRequest(JLPCActor targetActor, ResponseProcessor rp) throws Exception {
        _rp.processResponse(rsp);
        rp.processResponse(null);
    }
}
