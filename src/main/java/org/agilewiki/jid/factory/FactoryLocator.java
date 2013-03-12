/*
 * Copyright 2011 Bill La Forge
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
package org.agilewiki.jid.factory;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.Mailbox;
import org.agilewiki.jactor.lpc.TargetActor;
import org.agilewiki.jid.Jid;
import org.agilewiki.jid.collection.vlenc.map.StringMapJid;
import org.agilewiki.jid.scalar.vlens.string.StringJid;

/**
 * Defines actor types and instantiating
 */
public interface FactoryLocator extends TargetActor {
    /**
     * Bind an actor type to a Class.
     *
     * @param jidType The jid type.
     * @param clazz   The class of the actor.
     */
    public void defineJidType(String jidType, Class clazz)
            throws Exception;

    /**
     * Register an actor factory.
     *
     * @param actorFactory An actor factory.
     */
    public void registerJidFactory(ActorFactory actorFactory)
            throws Exception;

    /**
     * Returns the requested actor factory.
     *
     * @param jidType The jid type.
     * @return The registered actor factory.
     */
    public ActorFactory getJidFactory(String jidType)
            throws Exception;

    public ActorFactory _getActorFactory(String actorType)
            throws Exception;

    /**
     * Creates a new actor.
     *
     * @param jidType The jid type.
     * @return The new jid.
     */
    public Jid newJid(String jidType)
            throws Exception;

    /**
     * Creates a new actor.
     *
     * @param jidType The jid type.
     * @param mailbox A mailbox which may be shared with other actors, or null.
     * @return The new actor.
     */
    public Jid newJid(String jidType, Mailbox mailbox)
            throws Exception;

    /**
     * Creates a new actor.
     *
     * @param jidType The jid type.
     * @param mailbox A mailbox which may be shared with other actors, or null.
     * @param parent  The parent actor to which unrecognized requests are forwarded, or null.
     * @return The new actor.
     */
    public Jid newJid(String jidType, Mailbox mailbox, Actor parent)
            throws Exception;

    public StringMapJid<StringJid> getManifestCopy(Mailbox mailbox) throws Exception;

    public boolean validateManifest(StringMapJid<StringJid> m) throws Exception;

    public void loadBundles(StringMapJid<StringJid> m) throws Exception;

    public void unknownManifestEntries(StringMapJid<StringJid> m) throws Exception;
}
