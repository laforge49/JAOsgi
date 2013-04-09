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
package org.agilewiki.incdes.impl.factory;

import org.agilewiki.incdes.FactoryLocator;
import org.agilewiki.incdes.IncDesFactories;
import org.agilewiki.incdes.Util;
import org.agilewiki.incdes.impl.IncDesImpl;
import org.agilewiki.incdes.impl.collection.vlenc.map.MapEntryImpl;
import org.agilewiki.incdes.impl.collection.vlenc.map.StringSMap;
import org.agilewiki.incdes.impl.scalar.vlens.PAStringImpl;
import org.agilewiki.pactor.Actor;
import org.agilewiki.pactor.Mailbox;
import org.agilewiki.pautil.Ancestor;
import org.agilewiki.pautil.AncestorBase;
import org.osgi.framework.Bundle;

import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * An actor for defining jid types and creating instances.
 */
public class FactoryLocatorImpl extends AncestorBase implements FactoryLocator {

    private ConcurrentSkipListSet<LocateLocalActorFactories> factoryImports = new ConcurrentSkipListSet();
    private String bundleName = "";
    private String version = "";
    private String location = "";
    private String locatorKey;
    private StringSMap<PAStringImpl> manifest;

    /**
     * A table which maps type names to actor factories.
     */
    private ConcurrentSkipListMap<String, ActorFactory> types = new ConcurrentSkipListMap<String, ActorFactory>();

    private boolean isLocked() {
        return manifest != null;
    }

    public void configure(Bundle bundle) {
        bundleName = bundle.getSymbolicName();
        version = Util.versionString(bundle.getVersion());
        location = bundle.getLocation();
    }

    public void configure(String name) {
        bundleName = name;
    }

    public String getBundleName() {
        return bundleName;
    }

    public String getVersion() {
        return version;
    }

    public String getLocation() {
        return location;
    }

    public String getLocatorKey() {
        if (locatorKey == null)
            locatorKey = bundleName + "|" + getVersion();
        return locatorKey;
    }

    public void importFactories(LocateLocalActorFactories locateLocalActorFactories) {
        if (isLocked())
            throw new IllegalStateException("Once a manifest is built, imports are locked");
        factoryImports.add(locateLocalActorFactories);
    }

    @Override
    public StringSMap<PAStringImpl> getManifestCopy(Mailbox mailbox) throws Exception {
        if (isLocked())
            return manifest;
        manifest = (StringSMap<PAStringImpl>) newJid(IncDesFactories.STRING_STRING_MAP_JID_TYPE);
        manifest.kMake(getLocatorKey());
        PAStringImpl sj = manifest.kGet(getLocatorKey());
        sj.setValue(getLocation());
        Iterator<LocateLocalActorFactories> it = factoryImports.iterator();
        while (it.hasNext()) {
            LocateLocalActorFactories llaf = it.next();
            llaf.updateManifest(manifest);
        }
        return (StringSMap<PAStringImpl>) manifest.copyJID(mailbox);
    }

    public void updateManifest(StringSMap<PAStringImpl> m) throws Exception {
        int s = manifest.size();
        int i = 0;
        while (i < s) {
            MapEntryImpl<String, PAStringImpl> me = (MapEntryImpl) manifest.iGet(i);
            String locatorKey = me.getKey();
            String location = me.getValue().getValue();
            m.kMake(locatorKey);
            PAStringImpl sj = m.kGet(locatorKey);
            sj.setValue(location);
            i += 1;
        }
    }

    @Override
    public void unknownManifestEntries(StringSMap<PAStringImpl> m) throws Exception {
        m.kRemove(getLocatorKey());
        Iterator<LocateLocalActorFactories> it = factoryImports.iterator();
        while (it.hasNext()) {
            LocateLocalActorFactories llaf = it.next();
            llaf.unknownManifestEntries(manifest);
        }
    }

    @Override
    public boolean validateManifest(StringSMap<PAStringImpl> m) throws Exception {
        StringSMap<PAStringImpl> mc = (StringSMap<PAStringImpl>) m.copyJID(getMailbox());
        unknownManifestEntries(mc);
        return mc.size() == 0;
    }

    @Override
    public void loadBundles(StringSMap<PAStringImpl> m) throws Exception {
        if (!validateManifest(m))
            throw new UnsupportedOperationException("Did not load " + m.size() + " bundles");
    }

    /**
     * Creates a new actor.
     *
     * @param jidType The jid type.
     * @return The new jid.
     */
    public IncDesImpl newJid(String jidType)
            throws Exception {
        return newJid(jidType, null, null);
    }

    /**
     * Creates a new actor.
     *
     * @param jidType The jid type.
     * @param mailbox A mailbox which may be shared with other actors, or null.
     * @return The new jid.
     */
    public IncDesImpl newJid(String jidType, Mailbox mailbox)
            throws Exception {
        return newJid(jidType, mailbox, null);
    }

    /**
     * Creates a new actor.
     *
     * @param jidType The jid type.
     * @param mailbox A mailbox which may be shared with other actors, or null.
     * @param parent  The parent actor to which unrecognized requests are forwarded, or null.
     * @return The new jid.
     */
    public IncDesImpl newJid(String jidType, Mailbox mailbox, Ancestor parent)
            throws Exception {
        if (mailbox == null || parent == null) {
            if (mailbox == null) mailbox = getMailbox();
            if (parent == null) parent = this;
        }
        ActorFactory af = getJidFactory(jidType);
        return af.newActor(mailbox, parent);
    }

    /**
     * Returns the requested actor factory.
     *
     * @param jidType The jid type.
     * @return The registered actor factory.
     */
    @Override
    public ActorFactory getJidFactory(String jidType)
            throws Exception {
        ActorFactory af = _getActorFactory(jidType);
        if (af == null) {
            throw new IllegalArgumentException("Unknown jid type: " + jidType);
        }
        return af;
    }

    @Override
    public ActorFactory _getActorFactory(String actorType)
            throws Exception {
        String factoryKey = null;
        if (actorType.contains("|")) {
            factoryKey = actorType;
        } else {
            factoryKey = actorType + "|" + bundleName + "|" + version;
        }
        ActorFactory af = types.get(factoryKey);
        if (af == null) {
            Iterator<LocateLocalActorFactories> it = factoryImports.iterator();
            while (it.hasNext()) {
                af = it.next()._getActorFactory(actorType);
                if (af != null)
                    return af;
            }
        }
        return af;
    }

    /**
     * Bind an jid type to a Class.
     *
     * @param jidType The jid type.
     * @param clazz   The class of the actor.
     */
    @Override
    public void defineJidType(String jidType, Class clazz)
            throws Exception {
        String factoryKey = jidType + "|" + bundleName + "|" + version;
        if (types.containsKey(factoryKey))
            throw new IllegalArgumentException("IncDesImpl type is already defined: " + jidType);
        if (Actor.class.isAssignableFrom(clazz)) {
            Constructor componentConstructor = clazz.getConstructor();
            ActorFactory actorFactory = new _ActorFactory(jidType, componentConstructor);
            types.put(factoryKey, actorFactory);
            actorFactory.configure(this);
            return;
        }
        throw new IllegalArgumentException(clazz.getName());
    }

    /**
     * Register an actor factory.
     *
     * @param actorFactory An actor factory.
     */
    @Override
    public void registerJidFactory(ActorFactory actorFactory)
            throws Exception {
        String actorType = actorFactory.jidType;
        String factoryKey = actorType + "|" + bundleName + "|" + version;
        ActorFactory old = types.get(factoryKey);
        actorFactory.configure(this);
        if (old == null) {
            types.put(factoryKey, actorFactory);
        } else if (!old.equals(actorFactory))
            throw new IllegalArgumentException("IncDesImpl type is already defined: " + actorType);
    }
}