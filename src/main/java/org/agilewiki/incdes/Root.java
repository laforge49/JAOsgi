package org.agilewiki.incdes;

public interface Root extends Box {
    int save(final byte[] _bytes, final int _offset) throws Exception;

    int load(final byte[] _bytes, final int _offset, final int _length) throws Exception;

    void load(final byte[] _bytes)
            throws Exception;
}
