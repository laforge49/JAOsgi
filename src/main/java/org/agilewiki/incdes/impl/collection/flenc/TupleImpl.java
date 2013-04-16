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
package org.agilewiki.incdes.impl.collection.flenc;

import org.agilewiki.incdes.*;
import org.agilewiki.incdes.impl.ComparableKey;
import org.agilewiki.incdes.impl.IncDesImpl;
import org.agilewiki.incdes.impl.collection.CollectionImpl;

/**
 * Holds a fixed-size array of JID actors of various types.
 */
public class TupleImpl
        extends CollectionImpl<PASerializable>
        implements ComparableKey<Object>, Tuple {
    /**
     * An array of jid factories, one for each element in the tuple.
     */
    protected Factory[] tupleFactories;

    /**
     * A tuple of actors.
     */
    protected PASerializable[] tuple;

    /**
     * Perform lazy initialization.
     *
     * @throws Exception Any exceptions thrown during initialization.
     */
    private void initializeTuple()
            throws Exception {
        if (tuple != null)
            return;
        tupleFactories = getTupleFactories();
        ReadableBytes readableBytes = null;
        if (isSerialized()) {
            readableBytes = readable();
            skipLen(readableBytes);
        }
        tuple = new PASerializable[size()];
        int i = 0;
        len = 0;
        while (i < size()) {
            PASerializable elementJid = createSubordinate(tupleFactories[i], readableBytes);
            len += elementJid.getDurable().getSerializedLength();
            tuple[i] = elementJid;
            i += 1;
        }
    }

    /**
     * Returns the element factories.
     *
     * @return An array of element factories.
     */
    protected Factory[] getTupleFactories()
            throws Exception {
        if (tupleFactories != null)
            return tupleFactories;
        throw new IllegalStateException("tupleFactories is null");
    }

    /**
     * Creates a JID actor and loads its serialized data.
     *
     * @param i     The index of the desired element.
     * @param bytes Holds the serialized data.
     * @throws Exception Any exceptions thrown while processing the request.
     */
    @Override
    public void iSet(int i, byte[] bytes)
            throws Exception {
        initializeTuple();
        PASerializable elementJid = createSubordinate(tupleFactories[i], bytes);
        PASerializable oldElementJid = iGet(i);
        ((IncDesImpl) oldElementJid.getDurable()).setContainerJid(null);
        tuple[i] = elementJid;
        change(elementJid.getDurable().getSerializedLength() -
                oldElementJid.getDurable().getSerializedLength());
    }

    /**
     * Returns the number of bytes needed to serialize the persistent data.
     *
     * @return The minimum size of the byte array needed to serialize the persistent data.
     */
    @Override
    public int getSerializedLength()
            throws Exception {
        initializeTuple();
        return Util.INT_LENGTH + len;
    }

    /**
     * Returns the size of the collection.
     *
     * @return The size of the collection.
     */
    @Override
    public int size()
            throws Exception {
        return getTupleFactories().length;
    }

    /**
     * Returns the ith JID component.
     *
     * @param i The index of the element of interest.
     * @return The ith JID component, or null if the index is out of range.
     */
    @Override
    public PASerializable iGet(int i) throws Exception {
        initializeTuple();
        if (i < 0)
            i += size();
        if (i < 0 || i >= size())
            return null;
        return tuple[i];
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
        int i = 0;
        while (i < size()) {
            iGet(i).getDurable().save(appendableBytes);
            i += 1;
        }
    }

    /**
     * Load the serialized data into the JID.
     *
     * @param readableBytes Holds the serialized data.
     */
    @Override
    public void load(ReadableBytes readableBytes)
            throws Exception {
        super.load(readableBytes);
        len = loadLen(readableBytes);
        tuple = null;
        readableBytes.skip(len);
    }

    /**
     * Compares element 0
     *
     * @param o The comparison value.
     * @return The result of a compareTo(o) using element 0.
     */
    @Override
    public int compareKeyTo(Object o)
            throws Exception {
        ComparableKey<Object> e0 = (ComparableKey<Object>) iGet(0);
        return e0.compareKeyTo(o);
    }
}
