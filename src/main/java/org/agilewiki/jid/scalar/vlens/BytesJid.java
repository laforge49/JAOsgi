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
package org.agilewiki.jid.scalar.vlens;

import org.agilewiki.jactor.Request;
import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ancestor.Ancestor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jid.AppendableBytes;
import org.agilewiki.jid.ReadableBytes;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A JID component that holds a byte array.
 */
public class BytesJid
        extends VLenScalarJid<byte[], byte[]> {
    public static BytesJid create(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (BytesJid) JAFactoryLocator.newJid(actor, JidFactories.BYTES_JID_TYPE, mailbox, parent);
    }

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new ActorFactory(JidFactories.BYTES_JID_TYPE) {
            @Override
            final protected BytesJid instantiateActor()
                    throws Exception {
                return new BytesJid();
            }
        });
    }

    public Request<byte[]> getBytesReq;

    /**
     * Assign a value.
     *
     * @param v The new value.
     * @throws Exception Any uncaught exception raised.
     */
    @Override
    public void setValue(final byte[] v) throws Exception {
        int c = v.length;
        if (len > -1)
            c -= len;
        value = v;
        serializedBytes = null;
        serializedOffset = -1;
        change(c);
    }

    public Request<Void> setBytesReq(final byte[] v) {
        return new RequestBase<Void>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                setValue(v);
                rp.processResponse(null);
            }
        };
    }

    public void setObject(Object v) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(v);
        oos.close();
        byte[] bytes = baos.toByteArray();
        setValue(bytes);
    }

    /**
     * Assign a value unless one is already present.
     *
     * @param v The MakeValue request.
     * @return True if a new value is created.
     * @throws Exception Any uncaught exception raised.
     */
    @Override
    protected Boolean makeValue(final byte[] v) throws Exception {
        if (len > -1)
            return false;
        int c = v.length;
        if (len > -1)
            c -= len;
        value = v;
        serializedBytes = null;
        serializedOffset = -1;
        change(c);
        return true;
    }

    public Request<Boolean> makeBytesReq(final byte[] v) {
        return new RequestBase<Boolean>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                rp.processResponse(makeValue(v));
            }
        };
    }

    /**
     * Returns the value held by this component.
     *
     * @return The value held by this component, or null.
     */
    public byte[] getValue() {
        if (len == -1)
            return null;
        if (value != null)
            return value;
        ReadableBytes readableBytes = readable();
        skipLen(readableBytes);
        value = readableBytes.readBytes(len);
        return value;
    }

    public Object getObject() throws Exception {
        byte[] bytes = getValue();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    /**
     * Serialize the persistent data.
     *
     * @param appendableBytes The wrapped byte array into which the persistent data is to be serialized.
     */
    @Override
    protected void serialize(AppendableBytes appendableBytes) throws Exception {
        saveLen(appendableBytes);
        if (len == -1)
            return;
        appendableBytes.writeBytes(value);
    }

    public void initialize(final Mailbox mailbox, Ancestor parent, ActorFactory factory) throws Exception {
        getBytesReq = new RequestBase<byte[]>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                rp.processResponse(getValue());
            }
        };
    }
}