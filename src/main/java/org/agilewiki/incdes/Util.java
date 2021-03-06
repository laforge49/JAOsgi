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

import org.agilewiki.incdes.impl.collection.vlenc.map.StringSMap;
import org.agilewiki.incdes.impl.factory.FactoryLocatorImpl;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.pactor.Actor;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pactor.MailboxFactory;
import org.agilewiki.pactor.Properties;
import org.agilewiki.pautil.Ancestor;
import org.agilewiki.pautil.AncestorBase;
import org.osgi.framework.Version;

/**
 * <p>
 * Some common constants and methods.
 * </p>
 */
public class Util {
    public static String versionString(Version version) {
        return "" + version.getMajor() + "." + version.getMajor();
    }

    public static Context getContext(final Properties _properties) {
        return (Context) _properties.getProperty("context");
    }

    public static Context getContext(final MailboxFactory _mailboxFactory) {
        return getContext(_mailboxFactory.getProperties());
    }

    public static Context getContext(final Mailbox _mailbox) {
        return getContext(_mailbox.getMailboxFactory());
    }

    public static Context getContext(final Actor _actor) {
        return getContext(_actor.getMailbox());
    }

    public static FactoryLocator getFactoryLocator(final Actor _actor) {
        return getContext(_actor).getFactoryLocator();
    }

    public static FactoryLocator getFactoryLocator(final Mailbox _mailbox) {
        return getContext(_mailbox).getFactoryLocator();
    }

    public static Factory getActorFactory(final Actor _actor, final String _type)
            throws Exception {
        return getActorFactory(getFactoryLocator(_actor), _type);
    }

    public static Factory getActorFactory(FactoryLocator factoryLocator, String jidType)
            throws Exception {
        if (factoryLocator == null)
            throw new IllegalArgumentException("Unknown jid type: " + jidType);
        return factoryLocator.getFactory(jidType);
    }

    public static PASerializable newJid(Actor actor, String jidType, Mailbox mailbox, Ancestor parent)
            throws Exception {
        return newJid(getFactoryLocator(actor), jidType, mailbox, parent);
    }

    public static PASerializable newJid(FactoryLocator factoryLocator, String jidType, Mailbox mailbox, Ancestor parent)
            throws Exception {
        return factoryLocator.newJid(jidType, mailbox, parent);
    }

    public static StringSMap<PAStringImpl> getManifestCopy(Actor actor, Mailbox mailbox)
            throws Exception {
        FactoryLocator factoryLocator = getFactoryLocator(actor);
        if (factoryLocator == null)
            throw new IllegalStateException("FactoryLocator is not an ancestor");
        return factoryLocator.getManifestCopy(mailbox);
    }

    public static void unknownManifestEntries(Actor actor, StringSMap<PAStringImpl> m)
            throws Exception {
        FactoryLocator factoryLocator = getFactoryLocator(actor);
        if (factoryLocator == null)
            throw new IllegalStateException("FactoryLocator is not an ancestor");
        factoryLocator.unknownManifestEntries(m);
    }

    public static boolean validateManifest(Actor actor, StringSMap<PAStringImpl> m)
            throws Exception {
        FactoryLocator factoryLocator = getFactoryLocator(actor);
        if (factoryLocator == null)
            throw new IllegalStateException("FactoryLocator is not an ancestor");
        return factoryLocator.validateManifest(m);
    }

    public static void loadBundles(Actor actor, StringSMap<PAStringImpl> m)
            throws Exception {
        FactoryLocator factoryLocator = getFactoryLocator(actor);
        if (factoryLocator == null)
            throw new IllegalStateException("FactoryLocator is not an ancestor");
        factoryLocator.loadBundles(m);
    }

    /**
     * Size of a boolean in bytes.
     */
    public final static int BOOLEAN_LENGTH = 1;

    /**
     * Size of an int in bytes.
     */
    public final static int INT_LENGTH = 4;

    /**
     * Size of a long in bytes.
     */
    public final static int LONG_LENGTH = 8;

    /**
     * Size of an float in bytes.
     */
    public final static int FLOAT_LENGTH = 4;

    /**
     * Size of an double in bytes.
     */
    public final static int DOUBLE_LENGTH = 8;

    /**
     * Returns the number of bytes needed to write a string.
     *
     * @param length The number of characters in the string.
     * @return The size in bytes.
     */
    public final static int stringLength(int length) {
        if (length == -1)
            return INT_LENGTH;
        if (length > -1)
            return INT_LENGTH + 2 * length;
        throw new IllegalArgumentException("invalid string length: " + length);
    }

    /**
     * Returns the number of bytes needed to write a string.
     *
     * @param s The string.
     * @return The size in bytes.
     */
    public final static int stringLength(String s) {
        if (s == null)
            return INT_LENGTH;
        return stringLength(s.length());
    }
}
