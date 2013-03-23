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
package org.agilewiki.jid.scalar.flens;

import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.Request;
import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ancestor.Ancestor;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jid.AppendableBytes;
import org.agilewiki.jid.ReadableBytes;
import org.agilewiki.jid.Util;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * A JID actor that holds a long.
 */
public class LongJid
        extends FLenScalarJid<Long> {

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new ActorFactory(JidFactories.LONG_JID_TYPE) {
            @Override
            final protected LongJid instantiateActor()
                    throws Exception {
                return new LongJid();
            }
        });
    }

    public Request<Long> getLongReq;

    /**
     * Create the value.
     *
     * @return The default value
     */
    @Override
    protected Long newValue() {
        return new Long(0L);
    }

    /**
     * Returns the value held by this component.
     *
     * @return The value held by this component.
     */
    public Long getValue() {
        if (value != null)
            return value;
        ReadableBytes readableBytes = readable();
        value = readableBytes.readLong();
        return value;
    }

    public Request<Void> setLongReq(final Long v) {
        return new RequestBase<Void>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                setValue(getValue());
                rp.processResponse(null);
            }
        };
    }

    /**
     * Returns the number of bytes needed to serialize the persistent data.
     *
     * @return The minimum size of the byte array needed to serialize the persistent data.
     */
    @Override
    public int getSerializedLength() {
        return Util.LONG_LENGTH;
    }

    /**
     * Serialize the persistent data.
     *
     * @param appendableBytes The wrapped byte array into which the persistent data is to be serialized.
     */
    @Override
    protected void serialize(AppendableBytes appendableBytes) {
        appendableBytes.writeLong(value);
    }

    public void initialize(final Mailbox mailbox, Ancestor parent, ActorFactory factory) throws Exception {
        getLongReq = new RequestBase<Long>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                rp.processResponse(getValue());
            }
        };
    }
}