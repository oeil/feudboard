/*
 * Copyright (C) 2016 TekNux.org
 *
 * This file is part of the feudboard GPL Source Code.
 *
 * feudboard Source Code is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * feudboard Source Code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with dropbitz Community Source Code.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.teknux.feudboard.service.configuration;

import java.util.Objects;

import org.teknux.feudboard.Application;
import org.teknux.feudboard.config.app.Configuration;
import org.teknux.feudboard.service.IServiceManager;


public class ConfigurationServiceImpl implements
    IConfigurationService {

	private Configuration configuration;

	public ConfigurationServiceImpl() {
	}

	public void start(final Configuration configuration) {
	    this.configuration = Objects.requireNonNull(configuration, "Configuration can not be null");
	}
	
	@Override
	public void start(final IServiceManager serviceManager) {
		start(Application.getConfiguration());
	}

	@Override
	public void stop() {
	}

	public Configuration getConfiguration() {
		return configuration;
	}
}
