package org.agilewiki.incdes;

import org.agilewiki.jid.AppendableBytes;
import org.agilewiki.jid.ReadableBytes;
import org.agilewiki.jid.factory.ActorFactory;
import org.agilewiki.pautil.Ancestor;

public interface PAIncDes extends Ancestor {

    /**
     * Process a change in the persistent data.
     *
     * @param lengthChange The change in the size of the serialized data.
     * @throws Exception Any uncaught exception which occurred while processing the change.
     */
    void change(int lengthChange)
            throws Exception;

    /**
     * Assign the container.
     *
     * @param containerJid The container, or null.
     */
    void setContainerJid(PAIncDes containerJid) throws Exception;

    /**
     * Returns the number of bytes needed to serialize the persistent data.
     *
     * @return The minimum size of the byte array needed to serialize the persistent data.
     */
    int getSerializedLength()
            throws Exception;

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
}
