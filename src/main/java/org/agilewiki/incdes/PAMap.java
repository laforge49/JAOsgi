package org.agilewiki.incdes;

public interface PAMap<KEY_TYPE extends Comparable<KEY_TYPE>, VALUE_TYPE extends PAIncDes>
        extends PAList<PAMapEntry<KEY_TYPE, VALUE_TYPE>> {
}
