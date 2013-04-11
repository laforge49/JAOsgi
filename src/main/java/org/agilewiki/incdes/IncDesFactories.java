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
import org.agilewiki.incdes.impl.collection.flenc.TupleImpl;
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
    public final static String STRING_BLIST_JID_TYPE = "STRING_BLIST_JID";

    /**
     * The name of the BytesBListJid actor.
     */
    public final static String BYTES_BLIST_JID_TYPE = "BYTES_BLIST_JID";

    /**
     * The name of the ActorBListJid actor.
     */
    public final static String ACTOR_BLIST_JID_TYPE = "ACTOR_BLIST_JID";

    /**
     * The name of the LongBListJid actor.
     */
    public final static String LONG_BLIST_JID_TYPE = "LONG_BLIST_JID";

    /**
     * The name of the IntegerBListJid actor.
     */
    public final static String INTEGER_BLIST_JID_TYPE = "INTEGER_BLIST_JID";

    /**
     * The name of the FloatBListJid actor.
     */
    public final static String FLOAT_BLIST_JID_TYPE = "FLOAT_BLIST_JID";

    /**
     * The name of the DoubleBListJid actor.
     */
    public final static String DOUBLE_BLIST_JID_TYPE = "DOUBLE_BLIST_JID";

    /**
     * The name of the BooleanBListJid actor.
     */
    public final static String BOOLEAN_BLIST_JID_TYPE = "BOOLEAN_BLIST_JID";

    /**
     * The name of the StringListJid actor.
     */
    public final static String STRING_LIST_JID_TYPE = "STRING_LIST_JID";

    /**
     * The name of the BytesListJid actor.
     */
    public final static String BYTES_LIST_JID_TYPE = "BYTES_LIST_JID";

    /**
     * The name of the ActorListJid actor.
     */
    public final static String ACTOR_LIST_JID_TYPE = "ACTOR_LIST_JID";

    /**
     * The name of the LongListJid actor.
     */
    public final static String LONG_LIST_JID_TYPE = "LONG_LIST_JID";

    /**
     * The name of the IntegerListJid actor.
     */
    public final static String INTEGER_LIST_JID_TYPE = "INTEGER_LIST_JID";

    /**
     * The name of the FloatListJid actor.
     */
    public final static String FLOAT_LIST_JID_TYPE = "FLOAT_LIST_JID";

    /**
     * The name of the DoubleListJid actor.
     */
    public final static String DOUBLE_LIST_JID_TYPE = "DOUBLE_LIST_JID";

    /**
     * The name of the BooleanListJid actor.
     */
    public final static String BOOLEAN_LIST_JID_TYPE = "BOOLEAN_LIST_JID";

    /**
     * The name of the StringStringBMapJid actor.
     */
    public final static String STRING_STRING_BMAP_JID_TYPE = "STRING_STRING_BMAP_JID";

    /**
     * The name of the StringBytesBMapJid actor.
     */
    public final static String STRING_BYTES_BMAP_JID_TYPE = "STRING_BYTES_BMAP_JID";

    /**
     * The name of the StringActorBMapJid actor.
     */
    public final static String STRING_ACTOR_BMAP_JID_TYPE = "STRING_ACTOR_BMAP_JID";

    /**
     * The name of the StringLongBMapJid actor.
     */
    public final static String STRING_LONG_BMAP_JID_TYPE = "STRING_LONG_BMAP_JID";

    /**
     * The name of the StringIntegerBMapJid actor.
     */
    public final static String STRING_INTEGER_BMAP_JID_TYPE = "STRING_INTEGER_BMAP_JID";

    /**
     * The name of the StringFloatBMapJid actor.
     */
    public final static String STRING_FLOAT_BMAP_JID_TYPE = "STRING_FLOAT_BMAP_JID";

    /**
     * The name of the StringDoubleBMapJid actor.
     */
    public final static String STRING_DOUBLE_BMAP_JID_TYPE = "STRING_DOUBLE_BMAP_JID";

    /**
     * The name of the StringBooleanBMapJid actor.
     */
    public final static String STRING_BOOLEAN_BMAP_JID_TYPE = "STRING_BOOLEAN_BMAP_JID";

    /**
     * The name of the IntegerStringBMapJid actor.
     */
    public final static String INTEGER_STRING_BMAP_JID_TYPE = "INTEGER_STRING_BMAP_JID";

    /**
     * The name of the IntegerBytesBMapJid actor.
     */
    public final static String INTEGER_BYTES_BMAP_JID_TYPE = "INTEGER_BYTES_BMAP_JID";

    /**
     * The name of the IntegerActorBMapJid actor.
     */
    public final static String INTEGER_ACTOR_BMAP_JID_TYPE = "INTEGER_ACTOR_BMAP_JID";

    /**
     * The name of the IntegerLongBMapJid actor.
     */
    public final static String INTEGER_LONG_BMAP_JID_TYPE = "INTEGER_LONG_BMAP_JID";

    /**
     * The name of the IntegerIntegerBMapJid actor.
     */
    public final static String INTEGER_INTEGER_BMAP_JID_TYPE = "INTEGER_INTEGER_BMAP_JID";

    /**
     * The name of the IntegerFloatBMapJid actor.
     */
    public final static String INTEGER_FLOAT_BMAP_JID_TYPE = "INTEGER_FLOAT_BMAP_JID";

    /**
     * The name of the IntegerDoubleBMapJid actor.
     */
    public final static String INTEGER_DOUBLE_BMAP_JID_TYPE = "INTEGER_DOUBLE_BMAP_JID";

    /**
     * The name of the IntegerBooleanBMapJid actor.
     */
    public final static String INTEGER_BOOLEAN_BMAP_JID_TYPE = "INTEGER_BOOLEAN_BMAP_JID";

    /**
     * The name of the LongStringBMapJid actor.
     */
    public final static String LONG_STRING_BMAP_JID_TYPE = "LONG_STRING_BMAP_JID";

    /**
     * The name of the LongBytesBMapJid actor.
     */
    public final static String LONG_BYTES_BMAP_JID_TYPE = "LONG_BYTES_BMAP_JID";

    /**
     * The name of the LongActorBMapJid actor.
     */
    public final static String LONG_ACTOR_BMAP_JID_TYPE = "LONG_ACTOR_BMAP_JID";

    /**
     * The name of the LongLongBMapJid actor.
     */
    public final static String LONG_LONG_BMAP_JID_TYPE = "LONG_LONG_BMAP_JID";

    /**
     * The name of the LongIntegerBMapJid actor.
     */
    public final static String LONG_INTEGER_BMAP_JID_TYPE = "LONG_INTEGER_BMAP_JID";

    /**
     * The name of the LongFloatBMapJid actor.
     */
    public final static String LONG_FLOAT_BMAP_JID_TYPE = "LONG_FLOAT_BMAP_JID";

    /**
     * The name of the LongDoubleBMapJid actor.
     */
    public final static String LONG_DOUBLE_BMAP_JID_TYPE = "LONG_DOUBLE_BMAP_JID";

    /**
     * The name of the LongBooleanBMapJid actor.
     */
    public final static String LONG_BOOLEAN_BMAP_JID_TYPE = "LONG_BOOLEAN_BMAP_JID";

    /**
     * The name of the StringStringMapJid actor.
     */
    public final static String STRING_STRING_MAP_JID_TYPE = "STRING_STRING_MAP_JID";

    /**
     * The name of the StringBytesMapJid actor.
     */
    public final static String STRING_BYTES_MAP_JID_TYPE = "STRING_BYTES_MAP_JID";

    /**
     * The name of the StringActorMapJid actor.
     */
    public final static String STRING_ACTOR_MAP_JID_TYPE = "STRING_ACTOR_MAP_JID";

    /**
     * The name of the StringLongMapJid actor.
     */
    public final static String STRING_LONG_MAP_JID_TYPE = "STRING_LONG_MAP_JID";

    /**
     * The name of the StringIntegerMapJid actor.
     */
    public final static String STRING_INTEGER_MAP_JID_TYPE = "STRING_INTEGER_MAP_JID";

    /**
     * The name of the StringFloatMapJid actor.
     */
    public final static String STRING_FLOAT_MAP_JID_TYPE = "STRING_FLOAT_MAP_JID";

    /**
     * The name of the StringDoubleMapJid actor.
     */
    public final static String STRING_DOUBLE_MAP_JID_TYPE = "STRING_DOUBLE_MAP_JID";

    /**
     * The name of the StringBooleanMapJid actor.
     */
    public final static String STRING_BOOLEAN_MAP_JID_TYPE = "STRING_BOOLEAN_MAP_JID";

    /**
     * The name of the IntegerStringMapJid actor.
     */
    public final static String INTEGER_STRING_MAP_JID_TYPE = "INTEGER_STRING_MAP_JID";

    /**
     * The name of the IntegerBytesMapJid actor.
     */
    public final static String INTEGER_BYTES_MAP_JID_TYPE = "INTEGER_BYTES_MAP_JID";

    /**
     * The name of the IntegerActorMapJid actor.
     */
    public final static String INTEGER_ACTOR_MAP_JID_TYPE = "INTEGER_ACTOR_MAP_JID";

    /**
     * The name of the IntegerLongMapJid actor.
     */
    public final static String INTEGER_LONG_MAP_JID_TYPE = "INTEGER_LONG_MAP_JID";

    /**
     * The name of the IntegerIntegerMapJid actor.
     */
    public final static String INTEGER_INTEGER_MAP_JID_TYPE = "INTEGER_INTEGER_MAP_JID";

    /**
     * The name of the IntegerFloatMapJid actor.
     */
    public final static String INTEGER_FLOAT_MAP_JID_TYPE = "INTEGER_FLOAT_MAP_JID";

    /**
     * The name of the IntegerDoubleMapJid actor.
     */
    public final static String INTEGER_DOUBLE_MAP_JID_TYPE = "INTEGER_DOUBLE_MAP_JID";

    /**
     * The name of the IntegerBooleanMapJid actor.
     */
    public final static String INTEGER_BOOLEAN_MAP_JID_TYPE = "INTEGER_BOOLEAN_MAP_JID";

    /**
     * The name of the LongStringMapJid actor.
     */
    public final static String LONG_STRING_MAP_JID_TYPE = "LONG_STRING_MAP_JID";

    /**
     * The name of the LongBytesMapJid actor.
     */
    public final static String LONG_BYTES_MAP_JID_TYPE = "LONG_BYTES_MAP_JID";

    /**
     * The name of the LongActorMapJid actor.
     */
    public final static String LONG_ACTOR_MAP_JID_TYPE = "LONG_ACTOR_MAP_JID";

    /**
     * The name of the LongLongMapJid actor.
     */
    public final static String LONG_LONG_MAP_JID_TYPE = "LONG_LONG_MAP_JID";

    /**
     * The name of the LongIntegerMapJid actor.
     */
    public final static String LONG_INTEGER_MAP_JID_TYPE = "LONG_INTEGER_MAP_JID";

    /**
     * The name of the LongFloatMapJid actor.
     */
    public final static String LONG_FLOAT_MAP_JID_TYPE = "LONG_FLOAT_MAP_JID";

    /**
     * The name of the LongDoubleMapJid actor.
     */
    public final static String LONG_DOUBLE_MAP_JID_TYPE = "LONG_DOUBLE_MAP_JID";

    /**
     * The name of the LongBooleanMapJid actor.
     */
    public final static String LONG_BOOLEAN_MAP_JID_TYPE = "LONG_BOOLEAN_MAP_JID";

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

        BListFactory.registerFactory(factoryLocator, STRING_BLIST_JID_TYPE, PASTRING);
        BListFactory.registerFactory(factoryLocator, BYTES_BLIST_JID_TYPE, BYTES);
        BListFactory.registerFactory(factoryLocator, ACTOR_BLIST_JID_TYPE, BOX);
        BListFactory.registerFactory(factoryLocator, LONG_BLIST_JID_TYPE, PALONG);
        BListFactory.registerFactory(factoryLocator, INTEGER_BLIST_JID_TYPE, PAINTEGER);
        BListFactory.registerFactory(factoryLocator, FLOAT_BLIST_JID_TYPE, PAFLOAT);
        BListFactory.registerFactory(factoryLocator, DOUBLE_BLIST_JID_TYPE, PADOUBLE);
        BListFactory.registerFactory(factoryLocator, BOOLEAN_BLIST_JID_TYPE, PABOOLEAN);

        SListFactory.registerFactory(factoryLocator, STRING_LIST_JID_TYPE, PASTRING);
        SListFactory.registerFactory(factoryLocator, BYTES_LIST_JID_TYPE, BYTES);
        SListFactory.registerFactory(factoryLocator, ACTOR_LIST_JID_TYPE, BOX);
        SListFactory.registerFactory(factoryLocator, LONG_LIST_JID_TYPE, PALONG);
        SListFactory.registerFactory(factoryLocator, INTEGER_LIST_JID_TYPE, PAINTEGER);
        SListFactory.registerFactory(factoryLocator, FLOAT_LIST_JID_TYPE, PAFLOAT);
        SListFactory.registerFactory(factoryLocator, DOUBLE_LIST_JID_TYPE, PADOUBLE);
        SListFactory.registerFactory(factoryLocator, BOOLEAN_LIST_JID_TYPE, PABOOLEAN);

        StringSMapFactory.registerFactory(factoryLocator, STRING_STRING_MAP_JID_TYPE, PASTRING);
        StringSMapFactory.registerFactory(factoryLocator, STRING_BYTES_MAP_JID_TYPE, BYTES);
        StringSMapFactory.registerFactory(factoryLocator, STRING_ACTOR_MAP_JID_TYPE, BOX);
        StringSMapFactory.registerFactory(factoryLocator, STRING_LONG_MAP_JID_TYPE, PALONG);
        StringSMapFactory.registerFactory(factoryLocator, STRING_INTEGER_MAP_JID_TYPE, PAINTEGER);
        StringSMapFactory.registerFactory(factoryLocator, STRING_FLOAT_MAP_JID_TYPE, PAFLOAT);
        StringSMapFactory.registerFactory(factoryLocator, STRING_DOUBLE_MAP_JID_TYPE, PADOUBLE);
        StringSMapFactory.registerFactory(factoryLocator, STRING_BOOLEAN_MAP_JID_TYPE, PABOOLEAN);

        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_STRING_MAP_JID_TYPE, PASTRING);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_BYTES_MAP_JID_TYPE, BYTES);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_ACTOR_MAP_JID_TYPE, BOX);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_LONG_MAP_JID_TYPE, PALONG);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_INTEGER_MAP_JID_TYPE, PAINTEGER);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_FLOAT_MAP_JID_TYPE, PAFLOAT);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_DOUBLE_MAP_JID_TYPE, PADOUBLE);
        IntegerSMapFactory.registerFactory(factoryLocator, INTEGER_BOOLEAN_MAP_JID_TYPE, PABOOLEAN);

        LongSMapFactory.registerFactory(factoryLocator, LONG_STRING_MAP_JID_TYPE, PASTRING);
        LongSMapFactory.registerFactory(factoryLocator, LONG_BYTES_MAP_JID_TYPE, BYTES);
        LongSMapFactory.registerFactory(factoryLocator, LONG_ACTOR_MAP_JID_TYPE, BOX);
        LongSMapFactory.registerFactory(factoryLocator, LONG_LONG_MAP_JID_TYPE, PALONG);
        LongSMapFactory.registerFactory(factoryLocator, LONG_INTEGER_MAP_JID_TYPE, PAINTEGER);
        LongSMapFactory.registerFactory(factoryLocator, LONG_FLOAT_MAP_JID_TYPE, PAFLOAT);
        LongSMapFactory.registerFactory(factoryLocator, LONG_DOUBLE_MAP_JID_TYPE, PADOUBLE);
        LongSMapFactory.registerFactory(factoryLocator, LONG_BOOLEAN_MAP_JID_TYPE, PABOOLEAN);

        StringBMapFactory.registerFactory(factoryLocator, STRING_STRING_BMAP_JID_TYPE, PASTRING);
        StringBMapFactory.registerFactory(factoryLocator, STRING_BYTES_BMAP_JID_TYPE, BYTES);
        StringBMapFactory.registerFactory(factoryLocator, STRING_ACTOR_BMAP_JID_TYPE, BOX);
        StringBMapFactory.registerFactory(factoryLocator, STRING_LONG_BMAP_JID_TYPE, PALONG);
        StringBMapFactory.registerFactory(factoryLocator, STRING_INTEGER_BMAP_JID_TYPE, PAINTEGER);
        StringBMapFactory.registerFactory(factoryLocator, STRING_FLOAT_BMAP_JID_TYPE, PAFLOAT);
        StringBMapFactory.registerFactory(factoryLocator, STRING_DOUBLE_BMAP_JID_TYPE, PADOUBLE);
        StringBMapFactory.registerFactory(factoryLocator, STRING_BOOLEAN_BMAP_JID_TYPE, PABOOLEAN);

        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_STRING_BMAP_JID_TYPE, PASTRING);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_BYTES_BMAP_JID_TYPE, BYTES);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_ACTOR_BMAP_JID_TYPE, BOX);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_LONG_BMAP_JID_TYPE, PALONG);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_INTEGER_BMAP_JID_TYPE, PAINTEGER);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_FLOAT_BMAP_JID_TYPE, PAFLOAT);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_DOUBLE_BMAP_JID_TYPE, PADOUBLE);
        IntegerBMapFactory.registerFactory(factoryLocator, INTEGER_BOOLEAN_BMAP_JID_TYPE, PABOOLEAN);

        LongBMapFactory.registerFactory(factoryLocator, LONG_STRING_BMAP_JID_TYPE, PASTRING);
        LongBMapFactory.registerFactory(factoryLocator, LONG_BYTES_BMAP_JID_TYPE, BYTES);
        LongBMapFactory.registerFactory(factoryLocator, LONG_ACTOR_BMAP_JID_TYPE, BOX);
        LongBMapFactory.registerFactory(factoryLocator, LONG_LONG_BMAP_JID_TYPE, PALONG);
        LongBMapFactory.registerFactory(factoryLocator, LONG_INTEGER_BMAP_JID_TYPE, PAINTEGER);
        LongBMapFactory.registerFactory(factoryLocator, LONG_FLOAT_BMAP_JID_TYPE, PAFLOAT);
        LongBMapFactory.registerFactory(factoryLocator, LONG_DOUBLE_BMAP_JID_TYPE, PADOUBLE);
        LongBMapFactory.registerFactory(factoryLocator, LONG_BOOLEAN_BMAP_JID_TYPE, PABOOLEAN);
        return factoryLocator;
    }
}
