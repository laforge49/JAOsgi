package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAList<ENTRY_TYPE extends PAIncDes> extends PACollection<ENTRY_TYPE> {

    Request<Void> emptyReq();

    void empty()
            throws Exception;

    Request<Void> iAddReq(final int _i);

    void iAdd(int i)
            throws Exception;

    Request<Void> iAddReq(final int _i, final byte[] _bytes);

    void iAdd(int i, byte[] bytes)
            throws Exception;

    Request<Void> iRemoveReq(final int _i);

    void iRemove(int i)
            throws Exception;
}
