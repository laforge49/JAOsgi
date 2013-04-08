package org.agilewiki.incdes;

import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.Request;
import org.agilewiki.pautil.Ancestor;

public interface PAIncDes extends Ancestor {

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

    Request<Void> saveReq(final AppendableBytes appendableBytes);

    /**
     * Saves the persistent data in a byte array.
     *
     * @param appendableBytes Holds the byte array and offset.
     */
    void save(AppendableBytes appendableBytes)
            throws Exception;

    /**
     * Load the serialized data into the JID.
     *
     * @param readableBytes Holds the serialized data.
     */
    void load(ReadableBytes readableBytes)
            throws Exception;

    Request<PAIncDes> resolvePathnameReq(final String pathname);

    /**
     * Resolves a JID pathname, returning a JID actor or null.
     *
     * @param pathname A JID pathname.
     * @return A JID actor or null.
     * @throws Exception Any uncaught exception which occurred while processing the request.
     */
    PAIncDes resolvePathname(String pathname)
            throws Exception;

    /**
     * Returns the factory.
     *
     * @return The factory, or null.
     */
    ActorFactory getFactory();

    /**
     * Returns the jid type.
     *
     * @return The jid type, or null.
     */
    String getJidType();

    Request<PAIncDes> copyJIDReq(final Mailbox m);

    PAIncDes copyJID(final Mailbox m)
            throws Exception;

    Request<Boolean> isJidEqualReq(final PAIncDes jidA);
}
