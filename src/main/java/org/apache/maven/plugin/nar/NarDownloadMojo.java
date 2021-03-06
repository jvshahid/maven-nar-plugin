package org.apache.maven.plugin.nar;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.Iterator;
import java.util.List;

import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Downloads any dependent NAR files. This includes the noarch and aol type NAR files.
 * 
 * @goal nar-download
 * @phase generate-sources
 * @requiresProject
 * @requiresDependencyResolution
 * @author Mark Donszelmann
 */
public class NarDownloadMojo
    extends AbstractDownloadMojo
{

    public final void narExecute()
        throws MojoExecutionException, MojoFailureException
    {
        List narArtifacts = getNarManager().getNarDependencies( "compile" );
        if ( classifiers == null )
        {
            getNarManager().downloadAttachedNars( narArtifacts, remoteArtifactRepositories, artifactResolver, null );
        }
        else
        {
            for ( Iterator j = classifiers.iterator(); j.hasNext(); )
            {
                getNarManager().downloadAttachedNars( narArtifacts, remoteArtifactRepositories, artifactResolver,
                                                      (String) j.next() );
            }
        }
    }
}
