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
package org.agilewiki.jid.scalar.vlens.actor;

import org.agilewiki.jactor.Request;
import org.agilewiki.jactor.RequestBase;
import org.agilewiki.jactor.ResponseProcessor;
import org.agilewiki.jactor.ancestor.Ancestor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jid.*;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.scalar.vlens.VLenScalarJid;

/**
 * A JID actor that holds a JID actor.
 */
public class ActorJid
        extends VLenScalarJid<String, Jid> {
    public static ActorJid create(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (ActorJid) JAFactoryLocator.newJid(actor, JidFactories.ACTOR_JID_TYPE, mailbox, parent);
    }

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new ActorFactory(JidFactories.ACTOR_JID_TYPE) {
            @Override
            final protected ActorJid instantiateActor()
                    throws Exception {
                return new ActorJid();
            }
        });
    }

    private Request<Void> clearReq;
    private Request<Jid> getActorReq;

    public Request<Void> clearReq() {
        return clearReq;
    }

    public Request<Jid> getActorReq() {
        return getActorReq;
    }

    /**
     * Clear the content.
     *
     * @throws Exception Any uncaught exception raised.
     */
    @Override
    public void clear() throws Exception {
        if (len == -1)
            return;
        int l = len;
        if (value != null) {
            value.setContainerJid(null);
            value = null;
        }
        serializedBytes = null;
        serializedOffset = -1;
        change(-l);
        len = -1;
    }

    /**
     * Assign a value unless one is already present.
     *
     * @param jidType The MakeValue request.
     * @return True if a new value is created.
     * @throws Exception Any uncaught exception raised.
     */
    @Override
    public Boolean makeValue(final String jidType)
            throws Exception {
        if (len > -1)
            return false;
        setValue(jidType);
        return true;
    }

    public Request<Boolean> makeActorReq(final String jidType) {
        return new RequestBase<Boolean>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                rp.processResponse(makeValue(jidType));
            }
        };
    }

    /**
     * Assign a value.
     *
     * @param jidType The jid type.
     * @throws Exception Any uncaught exception raised.
     */
    @Override
    public void setValue(final String jidType)
            throws Exception {
        value = createSubordinate(jidType);
        int l = Util.stringLength(value.getFactory().getFactoryKey()) + value.getSerializedLength();
        change(l);
        serializedBytes = null;
        serializedOffset = -1;
    }

    public Request<Void> setActorReq(final String actorType) {
        return new RequestBase<Void>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                setValue(actorType);
                rp.processResponse(null);
            }
        };
    }

    /**
     * Assign a value.
     *
     * @param jidFactory The jid factory.
     * @throws Exception Any uncaught exception raised.
     */
    public void setValue(final ActorFactory jidFactory)
            throws Exception {
        value = createSubordinate(jidFactory);
        int l = Util.stringLength(jidFactory.getFactoryKey()) + value.getSerializedLength();
        change(l);
        serializedBytes = null;
        serializedOffset = -1;
    }

    /**
     * Creates a JID actor and loads its serialized data.
     *
     * @param jidType An jid type name.
     * @param bytes   The serialized data.
     * @throws Exception Any uncaught exception raised.
     */
    public void setJidBytes(final String jidType, final byte[] bytes)
            throws Exception {
        if (len > -1)
            clear();
        setBytes(jidType, bytes);
    }

    public Request<Void> setActorBytesReq(final String jidType, final byte[] bytes) {
        return new RequestBase<Void>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                setJidBytes(jidType, bytes);
                rp.processResponse(null);
            }
        };
    }

    /**
     * Creates a JID actor and loads its serialized data, unless a JID actor is already present.
     *
     * @param jidType An jid type name.
     * @param bytes   The serialized data.
     * @return True if a new value is created.
     * @throws Exception Any uncaught exception raised.
     */
    public Boolean makeJidBytes(final String jidType, final byte[] bytes)
            throws Exception {
        if (len > -1)
            return false;
        setBytes(jidType, bytes);
        return true;
    }

    public Request<Boolean> makeActorBytesReq(final String jidType, final byte[] bytes) {
        return new RequestBase<Boolean>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                rp.processResponse(makeJidBytes(jidType, bytes));
            }
        };
    }

    /**
     * Creates a JID actor and loads its serialized data.
     *
     * @param jidType The jid type.
     * @param bytes   The serialized data.
     * @throws Exception Any uncaught exception raised.
     */
    public void setBytes(String jidType, byte[] bytes)
            throws Exception {
        value = createSubordinate(jidType, bytes);
        int l = Util.stringLength(value.getFactory().getFactoryKey()) + value.getSerializedLength();
        change(l);
        serializedBytes = null;
        serializedOffset = -1;
    }

    /**
     * Creates a JID actor and loads its serialized data.
     *
     * @param jidFactory The jid factory.
     * @param bytes      The serialized data.
     * @throws Exception Any uncaught exception raised.
     */
    public void setBytes(ActorFactory jidFactory, byte[] bytes)
            throws Exception {
        value = createSubordinate(jidFactory, bytes);
        int l = Util.stringLength(jidFactory.getFactoryKey()) + value.getSerializedLength();
        change(l);
        serializedBytes = null;
        serializedOffset = -1;
    }

    /**
     * Returns the actor held by this component.
     *
     * @return The actor held by this component, or null.
     * @throws Exception Any uncaught exception raised during deserialization.
     */
    @Override
    public Jid getValue()
            throws Exception {
        if (len == -1)
            return null;
        if (value != null)
            return (Jid) value;
        if (len == -1) {
            return null;
        }
        ReadableBytes readableBytes = readable();
        skipLen(readableBytes);
        String factoryKey = readableBytes.readString();
        value = createSubordinate(factoryKey, readableBytes);
        return (Jid) value;
    }

    /**
     * Serialize the persistent data.
     *
     * @param appendableBytes The wrapped byte array into which the persistent data is to be serialized.
     */
    @Override
    protected void serialize(AppendableBytes appendableBytes)
            throws Exception {
        saveLen(appendableBytes);
        if (len == -1)
            return;
        String factoryKey = value.getFactory().getFactoryKey();
        appendableBytes.writeString(factoryKey);
        value.save(appendableBytes);
    }

    /**
     * Resolves a JID pathname, returning a JID actor or null.
     *
     * @param pathname A JID pathname.
     * @return A JID actor or null.
     * @throws Exception Any uncaught exception which occurred while processing the request.
     */
    @Override
    public _Jid resolvePathname(String pathname)
            throws Exception {
        if (pathname.length() == 0) {
            return this;
        }
        if (pathname.equals("0")) {
            return getValue();
        }
        if (pathname.startsWith("0/")) {
            _Jid v = getValue();
            if (v == null)
                return null;
            return v.resolvePathname(pathname.substring(2));
        }
        throw new IllegalArgumentException("pathname " + pathname);
    }

    public void initialize(final Mailbox mailbox, Ancestor parent, ActorFactory factory) throws Exception {
        clearReq = new RequestBase<Void>(this) {
            public void processRequest(ResponseProcessor rp) throws Exception {
                clear();
                rp.processResponse(null);
            }
        };

        getActorReq = new RequestBase<Jid>(this) {
            @Override
            public void processRequest(ResponseProcessor rp) throws Exception {
                rp.processResponse(getValue());
            }
        };
    }
}
