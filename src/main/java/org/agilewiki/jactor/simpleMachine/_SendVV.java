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
package org.agilewiki.jactor.simpleMachine;

import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.old.Actor;

/**
 * <p>
 * Send a request to an actor.
 * </p>
 * <pre>
 *            Doubler doubler = new Doubler(getMailbox());
 *            SMBuilder smb = new SMBuilder();
 *            smb._send(doubler, 21, "rsp");
 *            smb._return(new ObjectFunc() {
 *                public Object get(StateMachine sm) {
 *                    return sm.get("rsp");
 *                }
 *            });
 *            smb.call(rp);
 *
 *            Response:
 *            42
 * </pre>
 */
final public class _SendVV extends _Send {
    /**
     * The actor which is to receive the message.
     */
    private Actor targetActor;

    /**
     * The request.
     */
    private RequestBase request;

    /**
     * The name of the result, or null.
     */
    private String resultName;

    /**
     * Create a _SendVV.
     *
     * @param parentSMB   The parent builder.
     * @param targetActor The actor which is to receive the message.
     * @param request     The request.
     * @param resultName  The name of the result, or null.
     */
    public _SendVV(_SMBuilder parentSMB, Actor targetActor, RequestBase request, String resultName) {
        this.targetActor = targetActor;
        this.request = request;
        this.resultName = resultName;
        parentSMB.add(this);
    }

    /**
     * Returns the actor which is to receive the message.
     *
     * @param stateMachine The state machine.
     * @return The actor which is to receive the message.
     */
    @Override
    public Actor getTargetActor(SimpleMachine stateMachine) {
        return targetActor;
    }

    /**
     * Returns the request.
     *
     * @param stateMachine The state machine.
     * @return The request.
     */
    @Override
    public RequestBase getRequest(SimpleMachine stateMachine) {
        return request;
    }

    /**
     * Returns the name of the result, or null.
     *
     * @return The name of the result, or null.
     */
    @Override
    public String getResultName() {
        return resultName;
    }
}
