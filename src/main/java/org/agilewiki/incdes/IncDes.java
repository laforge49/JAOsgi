package org.agilewiki.incdes;

import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.Request;

public interface IncDes extends PASerializable {

    Request<Integer> getSerializedLengthReq();

    /**
     * Returns the number of bytes needed to serialize the persistent data.
     *
     * @return The minimum size of the byte array needed to serialize the persistent data.
     */
    int getSerializedLength()
            throws Exception;

    Request<byte[]> getSerializedBytesReq();

    byte[] getSerializedBytes()
            throws Exception;

    Request<Void> saveReq(final AppendableBytes _appendableBytes);

    /**
     * Saves the persistent data in a byte array.
     *
     * @param _appendableBytes Holds the byte array and offset.
     */
    void save(final AppendableBytes _appendableBytes)
            throws Exception;

    /**
     * Load the serialized data into the JID.
     *
     * @param _readableBytes Holds the serialized data.
     */
    void load(final ReadableBytes _readableBytes)
            throws Exception;

    Request<PASerializable> resolvePathnameReq(final String _pathname);

    /**
     * Resolves a JID pathname, returning a JID actor or null.
     *
     * @param _pathname A JID pathname.
     * @return A JID actor or null.
     * @throws Exception Any uncaught exception which occurred while processing the request.
     */
    PASerializable resolvePathname(final String _pathname)
            throws Exception;

    /**
     * Returns the factory.
     *
     * @return The factory, or null.
     */
    Factory getFactory();

    /**
     * Returns the jid type.
     *
     * @return The jid type, or null.
     */
    String getType();

    Request<PASerializable> copyReq(final Mailbox _m);

    PASerializable copy(final Mailbox m)
            throws Exception;

    Request<Boolean> isEqualReq(final PASerializable _jidA);
}
