/*
 * Copyright (C) 2009, 2010 Jayway AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jayway.maven.plugins.lab.mojo.run;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.jayway.maven.plugins.lab.LabRunner;
import com.jayway.maven.plugins.lab.mojo.AbstractLabMojo;


public abstract class AbstractRunLabMojo extends AbstractLabMojo {

	public final void execute() throws MojoExecutionException, MojoFailureException {
		assertLabInitialized();
		int currentStep = execute(createLabRunner());
		super.getLog().info("Current step is: " + currentStep);
	}

	protected abstract int execute(LabRunner lab);

}
