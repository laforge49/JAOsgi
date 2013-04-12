/*
 * Copyright 2012 Bill La Forge
 *
 * This file is part of AgileWiki and is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License (LGPL) as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * or navigate to the following url http://www.gnu.org/licenses/lgpl-2.1.txt
 *
 * Note however that only Scala, Java and JavaScript files are being covered by LGPL.
 * All other files are covered by the Common Public License (CPL).
 * A copy of this license is also included and can be
 * found as well at http://www.opensource.org/licenses/cpl1.0.txt
 */
package org.agilewiki.incdes;

import org.agilewiki.incdes.impl.IncDesFactory;
import org.agilewiki.incdes.impl.collection.flenc.TupleFactory;
import org.agilewiki.incdes.impl.collection.vlenc.BListFactory;
import org.agilewiki.incdes.impl.collection.vlenc.SListFactory;
import org.agilewiki.incdes.impl.collection.vlenc.map.*;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.incdes.impl.factory.LocateLocalActorFactories;
import org.agilewiki.incdes.impl.paosgi.PABundleContextImpl;
import org.agilewiki.incdes.impl.scalar.flens.*;
import org.agilewiki.incdes.impl.scalar.vlens.*;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.MailboxFactory;
import org.agilewiki.pautil.Ancestor;

/**
 * <p>
 * Defines IncDesImpl actor types and registers the JID factories.
 * </p>
 */
final public class IncDesFactories extends LocateLocalActorFactories {
    public static FactoryLocator createFactoryLocator() throws Exception {
        Context jaBundleContext = PABundleContextImpl.createNoOsgiJABundleContext();
        IncDesFactories jidFactories = new IncDesFactories();
        jidFactories.initialize(null, jaBundleContext);
        return jidFactories.configure();
    }

    public static FactoryLocator createFactoryLocator(MailboxFactory mailboxFactory) throws Exception {
        Context jaBundleContext = PABundleContextImpl.createNoOsgiJABundleContext(mailboxFactory);
        IncDesFactories jidFactories = new IncDesFactories();
        jidFactories.initialize(null, jaBundleContext);
        return jidFactories.configure();
    }

    public static void registerUnionFactory(final FactoryLocator _factoryLocator,
                                            final String _subActorType,
                                            final String... _actorTypes)
            throws Exception {
        UnionImpl.registerFactory(_factoryLocator, _subActorType, _actorTypes);
    }

    public static void registerTupleFactory(final FactoryLocator _factoryLocator,
                                            final String _subActorType,
                                            final String... _actorTypes)
            throws Exception {
        TupleFactory.registerFactory(_factoryLocator, _subActorType, _actorTypes);
    }

    /**
     * The name of the JID actor.
     */
    public final static String INCDES = "incdes";

    public static IncDes createIncDes(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (IncDes) Util.newJid(actor, INCDES, mailbox, parent);
    }

    /**
     * The name of the PABooleanImpl actor.
     */
    public final static String PABOOLEAN = "bool";

    public static PABoolean createPABoolean(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (PABoolean) Util.newJid(actor, PABOOLEAN, mailbox, parent);
    }

    /**
     * The name of the PAIntegerImpl actor.
     */
    public final static String PAINTEGER = "int";

    public static PAInteger createPAInteger(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (PAInteger) Util.newJid(actor, PAINTEGER, mailbox, parent);
    }

    /**
     * The name of the PALongImpl actor.
     */
    public final static String PALONG = "long";

    public static PALong createPALong(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (PALong) Util.newJid(actor, PALONG, mailbox, parent);
    }

    /**
     * The name of the PAFloatImpl actor.
     */
    public final static String PAFLOAT = "float";

    public static PAFloat createPAFloat(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (PAFloat) Util.newJid(actor, PAFLOAT, mailbox, parent);
    }

    /**
     * The name of the PADoubleImpl actor.
     */
    public final static String PADOUBLE = "double";

    public static PADouble createPADouble(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (PADouble) Util.newJid(actor, PADOUBLE, mailbox, parent);
    }

    /**
     * The name of the JidJid actor.
     */
    public final static String BOX = "box";

    public static Box createBox(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (Box) Util.newJid(actor, BOX, mailbox, parent);
    }

    /**
     * The name of the JidJid actor.
     */
    public final static String ROOT = "root";

    public static Root createRoot(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (Root) Util.newJid(actor, ROOT, mailbox, parent);
    }

    /**
     * The name of the String actor.
     */
    public final static String PASTRING = "string";

    public static PAString createPAString(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (PAString) Util.newJid(actor, PASTRING, mailbox, parent);
    }

    /**
     * The name of the BytesImpl actor.
     */
    public final static String BYTES = "bytes";

    public static Bytes createBytes(Ancestor actor, Mailbox mailbox, Ancestor parent) throws Exception {
        return (Bytes) Util.newJid(actor, BYTES, mailbox, parent);
    }

    /**
     * The name of the StringBListJid actor.
     */
    public final static String PASTRING_BLIST = "stringBList";

    /**
     * The name of the BytesBListJid actor.
     */
    public final static String BYTES_BLIST = "bytesBList";

    /**
     * The name of the ActorBListJid actor.
     */
    public final static String BOX_BLIST = "boxBList";

    /**
     * The name of the LongBListJid actor.
     */
    public final static String PALONG_BLIST = "longBList";

    /**
     * The name of the IntegerBListJid actor.
     */
    public final static String PAINTEGER_BLIST = "intBList";

    /**
     * The name of the FloatBListJid actor.
     */
    public final static String PAFLOAT_BLIST = "floatBList";

    /**
     * The name of the DoubleBListJid actor.
     */
    public final static String PADOUBLE_BLIST = "doubleBList";

    /**
     * The name of the BooleanBListJid actor.
     */
    public final static String PABOOLEAN_BLIST = "boolBList";

    /**
     * The name of the StringListJid actor.
     */
    public final static String PASTRING_LIST = "stringList";

    /**
     * The name of the BytesListJid actor.
     */
    public final static String BYTES_LIST = "bytesList";

    /**
     * The name of the ActorListJid actor.
     */
    public final static String BOX_LIST = "boxList";

    /**
     * The name of the LongListJid actor.
     */
    public final static String PALONG_LIST = "longList";

    /**
     * The name of the IntegerListJid actor.
     */
    public final static String PAINTEGER_LIST = "intList";

    /**
     * The name of the FloatListJid actor.
     */
    public final static String PAFLOAT_LIST = "floatList";

    /**
     * The name of the DoubleListJid actor.
     */
    public final static String PADOUBLE_LIST = "doubleList";

    /**
     * The name of the BooleanListJid actor.
     */
    public final static String PABOOLEAN_LIST = "boolList";

    /**
     * The name of the StringStringBMapJid actor.
     */
    public final static String STRING_PASTRING_BMAP = "stringStringBMap";

    /**
     * The name of the StringBytesBMapJid actor.
     */
    public final static String STRING_BYTES_BMAP = "stringBytesBMap";

    /**
     * The name of the StringActorBMapJid actor.
     */
    public final static String STRING_BOX_BMAP = "stringBoxBMap";

    /**
     * The name of the StringLongBMapJid actor.
     */
    public final static String STRING_PALONG_BMAP = "stringLongBMap";

    /**
     * The name of the StringIntegerBMapJid actor.
     */
    public final static String STRING_PAINTEGER_BMAP = "stringIntBMap";

    /**
     * The name of the StringFloatBMapJid actor.
     */
    public final static String STRING_PAFLOAT_BMAP = "stringFloatBMap";

    /**
     * The name of the StringDoubleBMapJid actor.
     */
    public final static String STRING_PADOUBLE_BMAP = "stringDoubleBMap";

    /**
     * The name of the StringBooleanBMapJid actor.
     */
    public final static String STRING_PABOOLEAN_BMAP = "stringBoolBMap";

    /**
     * The name of the IntegerStringBMapJid actor.
     */
    public final static String INTEGER_PASTRING_BMAP = "intStringBMap";

    /**
     * The name of the IntegerBytesBMapJid actor.
     */
    public final static String INTEGER_PABYTES_BMAP = "intBytesBMap";

    /**
     * The name of the IntegerActorBMapJid actor.
     */
    public final static String INTEGER_BOX_BMAP = "intBoxBMap";

    /**
     * The name of the IntegerLongBMapJid actor.
     */
    public final static String INTEGER_PALONG_BMAP = "intLongBMap";

    /**
     * The name of the IntegerIntegerBMapJid actor.
     */
    public final static String INTEGER_PAINTEGER_BMAP = "intIntBMap";

    /**
     * The name of the IntegerFloatBMapJid actor.
     */
    public final static String INTEGER_PAFLOAT_BMAP = "intFloatBMap";

    /**
     * The name of the IntegerDoubleBMapJid actor.
     */
    public final static String INTEGER_PADOUBLE_BMAP = "intDoubleBMap";

    /**
     * The name of the IntegerBooleanBMapJid actor.
     */
    public final static String INTEGER_PABOOLEAN_BMAP = "intBoolBMap";

    /**
     * The name of the LongStringBMapJid actor.
     */
    public final static String LONG_PASTRING_BMAP = "longStringBMap";

    /**
     * The name of the LongBytesBMapJid actor.
     */
    public final static String LONG_BYTES_BMAP = "longBytesBMap";

    /**
     * The name of the LongActorBMapJid actor.
     */
    public final static String LONG_BOX_BMAP = "longBoxBMap";

    /**
     * The name of the LongLongBMapJid actor.
     */
    public final static String LONG_PALONG_BMAP = "longLongBMap";

    /**
     * The name of the LongIntegerBMapJid actor.
     */
    public final static String LONG_PAINTEGER_BMAP = "longIntBMap";

    /**
     * The name of the LongFloatBMapJid actor.
     */
    public final static String LONG_PAFLOAT_BMAP = "longFloatBMap";

    /**
     * The name of the LongDoubleBMapJid actor.
     */
    public final static String LONG_PADOUBLE_BMAP = "longDoubleBMap";

    /**
     * The name of the LongBooleanBMapJid actor.
     */
    public final static String LONG_PABOOLEAN_BMAP = "longBoolBMap";

    /**
     * The name of the StringStringMapJid actor.
     */
    public final static String STRING_PASTRING_MAP = "stringStringMap";

    /**
     * The name of the StringBytesMapJid actor.
     */
    public final static String STRING_BYTES_MAP = "stringBytesMap";

    /**
     * The name of the StringActorMapJid actor.
     */
    public final static String STRING_BOX_MAP = "stringBoxMap";

    /**
     * The name of the StringLongMapJid actor.
     */
    public final static String STRING_PALONG_MAP = "stringLongMap";

    /**
     * The name of the StringIntegerMapJid actor.
     */
    public final static String STRING_PAINTEGER_MAP = "stringIntMap";

    /**
     * The name of the StringFloatMapJid actor.
     */
    public final static String STRING_PAFLOAT_MAP = "stringFloatMap";

    /**
     * The name of the StringDoubleMapJid actor.
     */
    public final static String STRING_PADOUBLE_MAP = "stringDoubleMap";

    /**
     * The name of the StringBooleanMapJid actor.
     */
    public final static String STRING_PABOOLEAN_MAP = "stringBoolMap";

    /**
     * The name of the IntegerStringMapJid actor.
     */
    public final static String INTEGER_PASTRING_MAP = "intStringMap";

    /**
     * The name of the IntegerBytesMapJid actor.
     */
    public final static String INTEGER_BYTES_MAP = "intBytesMap";

    /**
     * The name of the IntegerActorMapJid actor.
     */
    public final static String INTEGER_BOX_MAP = "intBoxMap";

    /**
     * The name of the IntegerLongMapJid actor.
     */
    public final static String INTEGER_PALONG_MAP = "intLongMap";

    /**
     * The name of the IntegerIntegerMapJid actor.
     */
    public final static String INTEGER_PAINTEGER_MAP = "intIntMap";

    /**
     * The name of the IntegerFloatMapJid actor.
     */
    public final static String INTEGER_PAFLOAT_MAP = "intFloatMap";

    /**
     * The name of the IntegerDoubleMapJid actor.
     */
    public final static String INTEGER_PADOUBLE_MAP = "intDoubleMap";

    /**
     * The name of the IntegerBooleanMapJid actor.
     */
    public final static String INTEGER_PABOOLEAN_MAP = "intBoolMap";

    /**
     * The name of the LongStringMapJid actor.
     */
    public final static String LONG_PASTRING_MAP = "longStringMap";

    /**
     * The name of the LongBytesMapJid actor.
     */
    public final static String LONG_BYTES_MAP = "longBytesMap";

    /**
     * The name of the LongActorMapJid actor.
     */
    public final static String LONG_BOX_MAP = "longBoxMap";

    /**
     * The name of the LongLongMapJid actor.
     */
    public final static String LONG_PALONG_MAP = "longLongMap";

    /**
     * The name of the LongIntegerMapJid actor.
     */
    public final static String LONG_PAINTEGER_MAP = "longIntMap";

    /**
     * The name of the LongFloatMapJid actor.
     */
    public final static String LONG_PAFLOAT_MAP = "longFloatMap";

    /**
     * The name of the LongDoubleMapJid actor.
     */
    public final static String LONG_PADOUBLE_MAP = "longDoubleMap";

    /**
     * The name of the LongBooleanMapJid actor.
     */
    public final static String LONG_PABOOLEAN_MAP = "longBoolMap";

    public FactoryLocatorImpl configure() throws Exception {
        FactoryLocatorImpl factoryLocator = configure("org.agilewiki.jid");

        IncDesFactory.registerFactory(factoryLocator);

        PABooleanImpl.registerFactory(factoryLocator);
        PAIntegerImpl.registerFactory(factoryLocator);
        PALongImpl.registerFactory(factoryLocator);
        PAFloatImpl.registerFactory(factoryLocator);
        PADoubleImpl.registerFactory(factoryLocator);

        BoxImpl.registerFactory(factoryLocator);
        RootImpl.registerFactory(factoryLocator);
        PAStringImpl.registerFactory(factoryLocator);
        BytesImpl.registerFactory(factoryLocator);

        BListFactory.registerFactory(factoryLocator, PASTRING_BLIST, PASTRING);
        BListFactory.registerFactory(factoryLocator, BYTES_BLIST, BYTES);
        BListFactory.registerFactory(factoryLocator, BOX_BLIST, BOX);
        BListFactory.registerFactory(factoryLocator, PALONG_BLIST, PALONG);
        BListFactory.registerFactory(factoryLocator, PAINTEGER_BLIST, PAINTEGER);
        BListFactory.registerFactory(factoryLocator, PAFLOAT_BLIST, PAFLOAT);
        BListFactory.registerFactory(factoryLocator, PADOUBLE_BLIST, PADOUBLE);
        BListFactory.registerFactory(factoryLocator, PABOOLEAN_BLIST, PABOOLEAN);

        SListFactory.registerFactory(factoryLocator, PASTRING_LIST, PASTRING);
        SListFactory.registerFactory(factoryLocator, BYTES_LIST, BYTES);
        SListFactory.registerFactory(factoryLocator, BOX_LIST, BOX);
        SListFactory.registerFactory(factoryLocator, PALONG_LIST, PALONG);
        SListFactory.registerFactory(factoryLocator, PAINTEGER_LIST, PAINTEGER);
        SListFactory.registerFactory(factoryLocator, PAFLOAT_LIST, PAFLOAT);
        SListFactory.registerFactory(factoryLocator, PADOUBLE_LIST, PADOUBLE);
        SListFactory.registerFactory(factoryLocator, PABOOLEAN_LIST, PABOOLEAN);

        StringSMapFactory.registerFactory(factoryLocator, STRING_PASTRING_MAP, PASTRING);
        StringSMapFactory.registerFactory(factoryLocator, STRING_BYTES_MAP, BYTES);
        StringSMapFactory.registerFactory(factoryLocator, STRING_BOX_MAP, BOX);
        StringSMapFactory.registerFactory(factoryLocator, STRING_PALONG_MAP, PALONG);
        StringSMapFactory.registerFactory(factoryLocator, STRING_PAINTEGER_MAP, PAINTEGER);
        StringSMapFactory.registerFactory(factoryLocator, STRING_PAFLOAT_MAP, PAFLOAT);
        StringSMapFactory.registerFactory(factoryLocator, STRING_PADOUBLE_MAP, PADOUBLE);
        StringSMapFactory.registerFactory(factoryLocator, STRING_PABOOLEAN_MAP, PABOOLEAN);

        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_PASTRING_MAP, PASTRING);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_BYTES_MAP, BYTES);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_BOX_MAP, BOX);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_PALONG_MAP, PALONG);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_PAINTEGER_MAP, PAINTEGER);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_PAFLOAT_MAP, PAFLOAT);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_PADOUBLE_MAP, PADOUBLE);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_PABOOLEAN_MAP, PABOOLEAN);

        LongSMapFactory.registerFactory(factoryLocator, LONG_PASTRING_MAP, PASTRING);
        LongSMapFactory.registerFactory(factoryLocator, LONG_BYTES_MAP, BYTES);
        LongSMapFactory.registerFactory(factoryLocator, LONG_BOX_MAP, BOX);
        LongSMapFactory.registerFactory(factoryLocator, LONG_PALONG_MAP, PALONG);
        LongSMapFactory.registerFactory(factoryLocator, LONG_PAINTEGER_MAP, PAINTEGER);
        LongSMapFactory.registerFactory(factoryLocator, LONG_PAFLOAT_MAP, PAFLOAT);
        LongSMapFactory.registerFactory(factoryLocator, LONG_PADOUBLE_MAP, PADOUBLE);
        LongSMapFactory.registerFactory(factoryLocator, LONG_PABOOLEAN_MAP, PABOOLEAN);

        StringBMapFactory.registerFactory(factoryLocator, STRING_PASTRING_BMAP, PASTRING);
        StringBMapFactory.registerFactory(factoryLocator, STRING_BYTES_BMAP, BYTES);
        StringBMapFactory.registerFactory(factoryLocator, STRING_BOX_BMAP, BOX);
        StringBMapFactory.registerFactory(factoryLocator, STRING_PALONG_BMAP, PALONG);
        StringBMapFactory.registerFactory(factoryLocator, STRING_PAINTEGER_BMAP, PAINTEGER);
        StringBMapFactory.registerFactory(factoryLocator, STRING_PAFLOAT_BMAP, PAFLOAT);
        StringBMapFactory.registerFactory(factoryLocator, STRING_PADOUBLE_BMAP, PADOUBLE);
        StringBMapFactory.registerFactory(factoryLocator, STRING_PABOOLEAN_BMAP, PABOOLEAN);

        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_PASTRING_BMAP, PASTRING);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_PABYTES_BMAP, BYTES);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_BOX_BMAP, BOX);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_PALONG_BMAP, PALONG);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_PAINTEGER_BMAP, PAINTEGER);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_PAFLOAT_BMAP, PAFLOAT);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_PADOUBLE_BMAP, PADOUBLE);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_PABOOLEAN_BMAP, PABOOLEAN);

        LongBMapFactory.registerFactory(factoryLocator, LONG_PASTRING_BMAP, PASTRING);
        LongBMapFactory.registerFactory(factoryLocator, LONG_BYTES_BMAP, BYTES);
        LongBMapFactory.registerFactory(factoryLocator, LONG_BOX_BMAP, BOX);
        LongBMapFactory.registerFactory(factoryLocator, LONG_PALONG_BMAP, PALONG);
        LongBMapFactory.registerFactory(factoryLocator, LONG_PAINTEGER_BMAP, PAINTEGER);
        LongBMapFactory.registerFactory(factoryLocator, LONG_PAFLOAT_BMAP, PAFLOAT);
        LongBMapFactory.registerFactory(factoryLocator, LONG_PADOUBLE_BMAP, PADOUBLE);
        LongBMapFactory.registerFactory(factoryLocator, LONG_PABOOLEAN_BMAP, PABOOLEAN);
        return factoryLocator;
    }
}
