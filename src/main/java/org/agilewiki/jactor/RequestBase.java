/*
 * Copyright 2012 Bill La Forge
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
package org.agilewiki.jactor;

import org.agilewiki.jactor.old.Actor;
import org.agilewiki.jactor.old.JAEvent;
import org.agilewiki.jactor.old.JAFuture;
import org.agilewiki.jactor.apc.APCRequestSource;

/**
 * A request.
 */
abstract public class RequestBase<RESPONSE_TYPE> implements Request<RESPONSE_TYPE> {
    protected Actor actor;

    public RequestBase(Actor actor) {
        this.actor = actor;
    }

    /**
     * Send a request and waits for a response.
     *
     * @param future      The future.
     * @throws Exception Any uncaught exceptions raised while processing the request.
     */
    @Override
    final public RESPONSE_TYPE send(JAFuture future)
            throws Exception {
        return (RESPONSE_TYPE) future.send(actor, this);
    }


    /**
     * Send a request.
     *
     * @param requestSource The sender of the request.
     * @param rp            The response processor.
     * @throws Exception Any uncaught exceptions raised while processing the request.
     */
    @Override
    final public void send(APCRequestSource requestSource, ResponseProcessor<RESPONSE_TYPE> rp)
            throws Exception {
        actor.acceptRequest(requestSource, this, rp);
    }

    /**
     * Send a request event.
     */
    @Override
    final public void sendEvent()
            throws Exception {
        actor.acceptEvent(JAEvent.requestSource, this);
    }

    /**
     * Send a request event.
     *
     * @param requestSource The sender of the request.
     */
    @Override
    final public void sendEvent(APCRequestSource requestSource)
            throws Exception {
        actor.acceptEvent(requestSource, this);
    }
}
