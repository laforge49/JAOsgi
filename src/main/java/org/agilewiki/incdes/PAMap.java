package org.agilewiki.incdes;

import org.agilewiki.pactor.Request;

public interface PAMap<KEY_TYPE extends Comparable<KEY_TYPE>, VALUE_TYPE extends PAIncDes>
        extends PAList<PAMapEntry<KEY_TYPE, VALUE_TYPE>> {

    Request<PAMapEntry<KEY_TYPE, VALUE_TYPE>> getFirstReq();

    Request<PAMapEntry<KEY_TYPE, VALUE_TYPE>> getLastReq();

    Request<VALUE_TYPE> kGetReq(final KEY_TYPE _key);

    Request<PAMapEntry<KEY_TYPE, VALUE_TYPE>> getHigherReq(final KEY_TYPE _key);

    Request<PAMapEntry<KEY_TYPE, VALUE_TYPE>> getCeilingReq(final KEY_TYPE _key);

    Request<Void> kSetReq(final KEY_TYPE _key, final byte[] _bytes);

    Request<Boolean> kMakeReq(final KEY_TYPE _key);

    Request<Boolean> kMakeReq(final KEY_TYPE _key, final byte[] _bytes);

    Request<Boolean> kRemoveReq(final KEY_TYPE _key);
}
