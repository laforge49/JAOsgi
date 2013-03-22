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
import org.agilewiki.jactor.old.RP;
import org.agilewiki.jid.AppendableBytes;
import org.agilewiki.jid.ComparableKey;
import org.agilewiki.jid.ReadableBytes;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;

/**
 * A JID actor that holds a String.
 */
public class StringJid
        extends VLenScalarJid<String, String>
        implements ComparableKey<String> {
    public static StringJid create(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (StringJid) JAFactoryLocator.newJid(actor, JidFactories.STRING_JID_TYPE, mailbox, parent);
    }

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new ActorFactory(JidFactories.STRING_JID_TYPE) {
            @Override
            final protected StringJid instantiateActor()
                    throws Exception {
                return new StringJid();
            }
        });
    }

    /**
     * Assign a value.
     *
     * @param v The new value.
     * @throws Exception Any uncaught exception raised.
     */
    @Override
    public void setValue(final String v) throws Exception {
        int c = v.length() * 2;
        if (len > -1)
            c -= len;
        value = v;
        serializedBytes = null;
        serializedOffset = -1;
        change(c);
    }

    public Request<Void> setStringReq(final String v) {
        if (v == null)
            throw new IllegalArgumentException("value may not be null");
        return new RequestBase<Void>(this) {
            @Override
            public void processRequest(RP rp) throws Exception {
                setValue(v);
                rp.processResponse(null);
            }
        };
    }

    /**
     * Assign a value unless one is already present.
     *
     * @param v The MakeValue request.
     * @return True if a new value is created.
     * @throws Exception Any uncaught exception raised.
     */
    @Override
    protected Boolean makeValue(String v) throws Exception {
        if (len > -1)
            return false;
        int c = v.length() * 2;
        if (len > -1)
            c -= len;
        value = v;
        serializedBytes = null;
        serializedOffset = -1;
        change(c);
        return true;
    }

    public Request<Boolean> makeStringReq(final String v) {
        if (v == null)
            throw new IllegalArgumentException("value may not be null");
        return new RequestBase<Boolean>(this) {
            @Override
            public void processRequest(RP rp) throws Exception {
                rp.processResponse(makeValue(v));
            }
        };
    }

    /**
     * Returns the value held by this component.
     *
     * @return The value held by this component, or null.
     */
    @Override
    public String getValue() {
        if (len == -1)
            return null;
        if (value != null)
            return value;
        ReadableBytes readableBytes = readable();
        skipLen(readableBytes);
        value = readableBytes.readString(len);
        return value;
    }

    public Request<String> getStringReq = new RequestBase<String>(this) {
        @Override
        public void processRequest(RP rp) throws Exception {
            rp.processResponse(getValue());
        }
    };

    /**
     * Serialize the persistent data.
     *
     * @param appendableBytes The wrapped byte array into which the persistent data is to be serialized.
     */
    @Override
    protected void serialize(AppendableBytes appendableBytes) throws Exception {
        if (len == -1)
            saveLen(appendableBytes);
        else
            appendableBytes.writeString(value);
    }

    /**
     * Compares the key or value;
     *
     * @param o The comparison value.
     * @return The result of a compareTo(o).
     */
    @Override
    public int compareKeyTo(String o) {
        return getValue().compareTo(o);
    }
}
