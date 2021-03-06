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
package org.agilewiki.incdes.impl.scalar.vlens;

import org.agilewiki.incdes.AppendableBytes;
import org.agilewiki.incdes.ReadableBytes;
import org.agilewiki.incdes.Util;
import org.agilewiki.incdes.impl.factory.FactoryImpl;
import org.agilewiki.incdes.impl.scalar.Scalar;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.Request;
import org.agilewiki.pactor.RequestBase;
import org.agilewiki.pactor.ResponseProcessor;
import org.agilewiki.pautil.Ancestor;

/**
 * A JID component that holds a variable-length value, or null.
 */
abstract public class VLenScalar<SET_TYPE, RESPONSE_TYPE>
        extends Scalar<SET_TYPE, RESPONSE_TYPE> {

    /**
     * Holds the value, or null.
     */
    protected RESPONSE_TYPE value = null;

    /**
     * The size of the serialized (exclusive of its length header).
     */
    protected int len = -1;

    private Request<Void> clearReq;

    public Request<Void> clearReq() {
        return clearReq;
    }

    /**
     * Assign a value unless one is already present.
     *
     * @param v The value.
     * @return True if a new value is created.
     * @throws Exception Any uncaught exception raised.
     */
    abstract public Boolean makeValue(SET_TYPE v)
            throws Exception;

    /**
     * Clear the content.
     *
     * @throws Exception Any uncaught exception raised.
     */
    public void clear() throws Exception {
        if (len == -1)
            return;
        int l = len;
        value = null;
        serializedBytes = null;
        serializedOffset = -1;
        change(-l);
        len = -1;
    }

    /**
     * Returns the number of bytes needed to serialize the persistent data.
     *
     * @return The minimum size of the byte array needed to serialize the persistent data.
     */
    @Override
    public int getSerializedLength() {
        if (len == -1)
            return Util.INT_LENGTH;
        return Util.INT_LENGTH + len;
    }

    /**
     * Returns the size of the serialized data (exclusive of its length header).
     *
     * @param readableBytes Holds the serialized data.
     * @return The size of the serialized data (exclusive of its length header).
     */
    protected int loadLen(ReadableBytes readableBytes) throws Exception {
        int l = readableBytes.readInt();
        return l;
    }

    /**
     * Writes the size of the serialized data (exclusive of its length header).
     *
     * @param appendableBytes The object written to.
     */
    protected void saveLen(AppendableBytes appendableBytes) throws Exception {
        appendableBytes.writeInt(len);
    }

    /**
     * Skip over the length at the beginning of the serialized data.
     *
     * @param readableBytes Holds the serialized data.
     */
    protected void skipLen(ReadableBytes readableBytes) {
        readableBytes.skip(Util.INT_LENGTH);
    }

    /**
     * Process a change in the persistent data.
     *
     * @param lengthChange The change in the size of the serialized data.
     * @throws Exception Any uncaught exception which occurred while processing the change.
     */
    @Override
    public void change(int lengthChange) throws Exception {
        if (len == -1)
            len = lengthChange;
        else
            len += lengthChange;
        super.change(lengthChange);
    }

    /**
     * Assigns the serialized data to the JID.
     *
     * @param readableBytes Holds the immutable serialized data.
     */
    @Override
    public void load(ReadableBytes readableBytes)
            throws Exception {
        super.load(readableBytes);
        len = loadLen(readableBytes);
        value = null;
        if (len > -1)
            readableBytes.skip(len);
    }

    public void initialize(final Mailbox mailbox, Ancestor parent, FactoryImpl factory) throws Exception {
        super.initialize(mailbox, parent, factory);
        clearReq = new RequestBase<Void>(getMailbox()) {
            public void processRequest(ResponseProcessor rp) throws Exception {
                clear();
                rp.processResponse(null);
            }
        };
    }
}
