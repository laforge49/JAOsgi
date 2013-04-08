package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAList<ENTRY_TYPE extends PAIncDes> extends PACollection<ENTRY_TYPE> {

    Request<Void> emptyReq();

    public void empty()
            throws Exception;

    Request<Void> iAddReq(final int _i);

    public void iAdd(int i)
            throws Exception;

    Request<Void> iAddReq(final int _i, final byte[] _bytes);

    public void iAdd(int i, byte[] bytes)
            throws Exception;

    Request<Void> iRemoveReq(final int _i);

    public void iRemove(int i)
            throws Exception;
}
