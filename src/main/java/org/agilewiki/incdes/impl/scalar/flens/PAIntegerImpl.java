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
package org.agilewiki.incdes.impl.scalar.flens;

import org.agilewiki.incdes.*;
import org.agilewiki.incdes.Util;
import org.agilewiki.incdes.impl.factory.ActorFactory;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.Request;
import org.agilewiki.pactor.RequestBase;
import org.agilewiki.pactor.ResponseProcessor;
import org.agilewiki.pautil.Ancestor;

/**
 * A JID actor that holds an integer.
 */
public class PAIntegerImpl
        extends FLenScalar<Integer> implements PAInteger {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new ActorFactory(PAFactories.INTEGER_JID_TYPE) {
            @Override
            final protected PAIntegerImpl instantiateActor()
                    throws Exception {
                return new PAIntegerImpl();
            }
        });
    }

    private Request<Integer> getIntegerReq;

    @Override
    public Request<Integer> getIntegerReq() {
        return getIntegerReq;
    }

    /**
     * Create the value.
     *
     * @return The default value
     */
    @Override
    protected Integer newValue() {
        return new Integer(0);
    }

    /**
     * Returns the value held by this component.
     *
     * @return The value held by this component.
     */
    @Override
    public Integer getValue() {
        if (value != null)
            return value;
        ReadableBytes readableBytes = readable();
        value = readableBytes.readInt();
        return value;
    }

    /**
     * Returns the number of bytes needed to serialize the persistent data.
     *
     * @return The minimum size of the byte array needed to serialize the persistent data.
     */
    @Override
    public int getSerializedLength() {
        return Util.INT_LENGTH;
    }

    /**
     * Serialize the persistent data.
     *
     * @param appendableBytes The wrapped byte array into which the persistent data is to be serialized.
     */
    @Override
    protected void serialize(AppendableBytes appendableBytes) {
        appendableBytes.writeInt(value);
    }

    @Override
    public Request<Void> setIntegerReq(final Integer v) {
        return new RequestBase<Void>(getMailbox()) {
            @Override
            public void processRequest(ResponseProcessor<Void> rp) throws Exception {
                setValue(v);
                rp.processResponse(null);
            }
        };
    }

    @Override
    public void initialize(final Mailbox mailbox, Ancestor parent, ActorFactory factory) throws Exception {
        super.initialize(mailbox, parent, factory);
        getIntegerReq = new RequestBase<Integer>(getMailbox()) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                rp.processResponse(getValue());
            }
        };
    }
}
