package org.agilewiki.incdes;

import org.agilewiki.jid.ComparableKey;

public interface MapEntry<KEY_TYPE extends Comparable<KEY_TYPE>, VALUE_TYPE>
        extends IncDes, ComparableKey<KEY_TYPE> {

    KEY_TYPE getKey() throws Exception;

    VALUE_TYPE getValue() throws Exception;
}
