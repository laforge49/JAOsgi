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
package org.agilewiki.jactor.pubsub.subscriber;

import org.agilewiki.jactor.old.RP;
import org.agilewiki.jactor.pubsub.actorName.ActorName;
import org.agilewiki.jactor.pubsub.publisher.Publisher;

/**
 * An actor that can be given a subscription.
 */
public interface Subscriber extends ActorName {
    /**
     * This actor has been granted a subscription.
     *
     * @param publisher The publisher that has been subscribed to.
     */
    public void subscribed(Publisher publisher)
            throws Exception;

    /**
     * This actor's subscription has been dropped.
     *
     * @param publisher The publisher which has dropped the subscription.
     * @param rp        The response processor.
     */
    public void unsubscribed(Publisher publisher, RP rp)
            throws Exception;
}
