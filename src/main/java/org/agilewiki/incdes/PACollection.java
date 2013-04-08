package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PACollection<ENTRY_TYPE extends PAIncDes> extends PAIncDes {

    Request<Integer> sizeReq();

    /**
     * Returns the size of the collection.
     *
     * @return The size of the collection.
     */
    int size()
            throws Exception;

    Request<ENTRY_TYPE> iGetReq(final int _i);

    /**
     * Returns the selected element.
     *
     * @param ndx Selects the element.
     * @return The ith JID component, or null if the index is out of range.
     */
    ENTRY_TYPE iGet(int ndx)
            throws Exception;

    Request<Void> iSetReq(final int _i, final byte[] _bytes);

    /**
     * Creates a JID actor and loads its serialized data.
     *
     * @param i     The index of the desired element.
     * @param bytes Holds the serialized data.
     * @throws Exception Any exceptions thrown while processing the request.
     */
    void iSet(int i, byte[] bytes)
            throws Exception;
}
