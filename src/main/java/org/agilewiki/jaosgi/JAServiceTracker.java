/*
 * Copyright 2013 Bill La Forge
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
package org.agilewiki.jaosgi;

import org.osgi.framework.*;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Map;

public class JAServiceTracker extends ServiceTracker {
    private HashSet<ServiceReference> references = new HashSet<ServiceReference>();
    final Logger logger = LoggerFactory.getLogger(ConfigUpdater.class);

    public JAServiceTracker(BundleContext context) {
        super(context, new Filter() {
            @Override
            public boolean match(ServiceReference reference) {
                return true;
            }

            @Override
            public boolean match(Dictionary dictionary) {
                return true;
            }

            @Override
            public boolean matchCase(Dictionary dictionary) {
                return true;
            }

            @Override
            public boolean matches(Map map) {
                return true;
            }
        }, null);
    }

    @Override
    public Object addingService(ServiceReference reference) {
        references.add(reference);
        return super.addingService(reference);
    }

    @Override
    public void removedService(ServiceReference reference, Object service) {
        super.removedService(reference, service);
        try {
            if (references.contains(reference))
                context.getBundle().stop(Bundle.STOP_TRANSIENT);
        } catch (BundleException e) {
            logger.error("unable to stop", e);
            throw new RuntimeException("unable to stop", e);
        }
    }
}
