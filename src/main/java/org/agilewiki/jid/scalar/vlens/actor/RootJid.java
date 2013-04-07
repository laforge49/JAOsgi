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

import org.agilewiki.incdes.PARoot;
import org.agilewiki.jid.AppendableBytes;
import org.agilewiki.jid.Jid;
import org.agilewiki.jid.ReadableBytes;
import org.agilewiki.jid._Jid;
import org.agilewiki.jid.collection.vlenc.map.StringMapJid;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.jid.factory.FactoryLocator;
import org.agilewiki.jid.factory.JAFactoryLocator;
import org.agilewiki.jid.factory.JidFactories;
import org.agilewiki.jid.scalar.vlens.StringJid;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * The root Jid actor of a tree of Jid actors.
 * <p/>
 * The serialized form of RootJid does NOT contain its length.
 * The load method simply grabs all the remaining data.
 */
public class RootJid extends ActorJid implements PARoot {
    public static RootJid create(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (RootJid) JAFactoryLocator.newJid(actor, JidFactories.ROOT_JID_TYPE, mailbox, parent);
    }

    public static void registerFactory(FactoryLocator factoryLocator)
            throws Exception {
        factoryLocator.registerJidFactory(new ActorFactory(JidFactories.ROOT_JID_TYPE) {
            @Override
            final protected RootJid instantiateActor()
                    throws Exception {
                return new RootJid();
            }
        });
    }

    private StringMapJid<StringJid> manifest;

    /**
     * Save the serialized data into a byte array.
     *
     * @param bytes  Where the serialized data is to be saved.
     * @param offset Location of the serialized data.
     * @return Updated offset.
     */
    public int save(byte[] bytes, int offset) throws Exception {
        AppendableBytes appendableBytes = new AppendableBytes(bytes, offset);
        save(appendableBytes);
        return appendableBytes.getOffset();
    }

    /**
     * Load the serialized data into the RootJid.
     *
     * @param bytes  A mutable array holding the serialized data.
     * @param offset Position of the serialized data.
     * @param length Length of the serialized data
     * @return The updated offset.
     */
    public int load(byte[] bytes, int offset, int length) throws Exception {
        byte[] bs = new byte[length];
        System.arraycopy(bytes, offset, bs, 0, length);
        load(bs);
        return offset + length;
    }

    /**
     * Assigns the serialized data to the RootJid.
     *
     * @param bytes Holds the immutable serialized data.
     *              (And nothing else.)
     */
    public void load(byte[] bytes)
            throws Exception {
        ReadableBytes rb = new ReadableBytes(bytes, 0);
        load(rb);
    }

    /**
     * Throws an UnsupportedOperationException,
     * as a RootJid does NOT have a container.
     *
     * @param containerJid The container, or null.
     */
    @Override
    public void setContainerJid(_Jid containerJid) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the size of the serialized data (exclusive of its length header).
     *
     * @param readableBytes Holds the serialized data.
     * @return The size of the remaining bytes of serialized data.
     */
    @Override
    protected int loadLen(ReadableBytes readableBytes) throws Exception {
        manifest.load(readableBytes);
        loadBundles();
        int l = readableBytes.remaining();
        if (l == 0)
            return -1;
        return l;
    }

    /**
     * There is no length, so there is nothing to skip over.
     *
     * @param readableBytes Holds the serialized data.
     */
    @Override
    protected void skipLen(ReadableBytes readableBytes) {
        readableBytes.skip(manifest.getSerializedLength());
    }

    /**
     * The length is not saved.
     *
     * @param appendableBytes The object written to.
     */
    @Override
    protected void saveLen(AppendableBytes appendableBytes) throws Exception {
        manifest.save(appendableBytes);
    }

    /**
     * Returns the number of bytes needed to serialize the persistent data.
     *
     * @return The minimum size of the byte array needed to serialize the persistent data.
     */
    @Override
    public int getSerializedLength() {
        if (len == -1)
            return manifest.getSerializedLength();
        return manifest.getSerializedLength() + len;
    }

    @Override
    public void initialize(final Mailbox mailbox, Ancestor parent, ActorFactory factory) throws Exception {
        super.initialize(mailbox, parent, factory);
        manifest = JAFactoryLocator.getManifestCopy(this, getMailbox());
    }

    public Jid copyJID(Mailbox m)
            throws Exception {
        Mailbox mb = m;
        if (mb == null)
            mb = getMailbox();
        Jid jid = getFactory().newActor(mb, getParent());
        jid.load(new ReadableBytes(getSerializedBytes(), 0));
        return jid;
    }

    public StringMapJid<StringJid> getManifestJidCopy(Mailbox mailbox) throws Exception {
        return (StringMapJid<StringJid>) manifest.copyJID(mailbox);
    }

    public boolean validateManifest() throws Exception {
        return JAFactoryLocator.validateManifest(this, manifest);
    }

    public void loadBundles() throws Exception {
        JAFactoryLocator.loadBundles(this, manifest);
    }

    public void unknownManifestEntries() throws Exception {
        JAFactoryLocator.unknownManifestEntries(this, manifest);
    }
}
