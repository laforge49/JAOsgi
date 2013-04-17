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
package org.agilewiki.jfile.block;

import org.agilewiki.incdes.*;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;

/**
 * A block with a length in the header.
 * --A minimal block implementation.
 */
public class LBlock implements Block {
    private String fileName;
    private long currentPosition;
    protected ReadableBytes rb;
    int l;
    protected byte[] blockBytes;
    protected byte[] rootJidBytes;
    private Root rootJid;

    /**
     * Reset the block and assign the RootImpl.
     *
     * @param rootJid The RootImpl to be assigned.
     */
    @Override
    public void setRootJid(Root rootJid) {
        blockBytes = null;
        rootJidBytes = null;
        this.rootJid = rootJid;
    }

    /**
     * Serializes the header and the assigned RootImpl.
     *
     * @return The bytes of the header and serialized RootImpl.
     */
    @Override
    public byte[] serialize()
            throws Exception {
        if (blockBytes != null)
            return blockBytes;
        l = rootJid.getSerializedLength();
        blockBytes = new byte[headerLength() + l];
        AppendableBytes ab = new AppendableBytes(blockBytes, headerLength());
        rootJid.save(ab);
        ab = new AppendableBytes(blockBytes, 0);
        saveHeader(ab, l);
        return blockBytes;
    }

    /**
     * Provides the raw header information to be written to disk.
     *
     * @param ab Append the data to this.
     * @param l  The length of the data.
     */
    protected void saveHeader(AppendableBytes ab, int l)
            throws Exception {
        ab.writeInt(l);
    }

    /**
     * The length of the header which prefaces the actual data on disk.
     *
     * @return The header length.
     */
    @Override
    public int headerLength() {
        return Util.INT_LENGTH;
    }

    /**
     * Returns the file name.
     *
     * @return The file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Assigns the file's name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the file position.
     *
     * @return The file position.
     */
    @Override
    public long getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Assigns the files current position.
     */
    @Override
    public void setCurrentPosition(long position) {
        currentPosition = position;
    }

    /**
     * Provides the raw header information read from disk.
     *
     * @param bytes The header bytes read from disk.
     * @return The length of the data following the header on disk.
     */
    @Override
    public int setHeaderBytes(byte[] bytes) {
        rb = new ReadableBytes(bytes, 0);
        l = rb.readInt();
        return l;
    }

    /**
     * Provides the data read from disk after the header.
     *
     * @param rootJidBytes The data following the header on disk.
     * @return True when the data is valid.
     */
    @Override
    public boolean setRootJidBytes(byte[] rootJidBytes) {
        if (l != rootJidBytes.length) {
            System.out.println("wrong length");
            return false;
        }
        this.rootJidBytes = rootJidBytes;
        return true;
    }

    /**
     * Get an existing RootImpl.
     *
     * @return The RootImpl.
     * @throws Exception An exception is thrown when there is no RootImpl.
     */
    public Root getRootJid()
            throws Exception {
        if (rootJid == null) {
            throw new IllegalStateException("there is no RootImpl");
        }
        return rootJid;
    }

    /**
     * Return the RootImpl, deserializing it as needed..
     *
     * @param mailbox The mailbox.
     * @param parent  The parent.
     * @return The deserialized RootImpl, or null.
     */
    @Override
    public Root getRootJid(FactoryLocator factoryLocator, Mailbox mailbox, Ancestor parent)
            throws Exception {
        if (rootJid != null)
            return rootJid;
        rb = null;
        if (rootJidBytes == null)
            return null;
        rootJid = IncDesFactories.createRoot(factoryLocator, mailbox, parent);
        rootJid.load(new ReadableBytes(rootJidBytes, 0));
        return rootJid;
    }

    /**
     * Indicates the abscense of a root jic.
     *
     * @return True when a root jit is not present.
     */
    public boolean isEmpty() {
        return rootJid == null && rootJidBytes == null;
    }

    /**
     * Returns the timestamp.
     *
     * @return The timestamp.
     */
    @Override
    public long getTimestamp() {
        throw new UnsupportedOperationException();
    }

    /**
     * Assigns the timestamp.
     *
     * @param timestamp The timestamp.
     */
    @Override
    public void setTimestamp(long timestamp) {
        throw new UnsupportedOperationException();
    }
}
