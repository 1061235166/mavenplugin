package com.wyk;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.IOUtil;

import java.io.*;
import java.util.jar.*;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 * 
 * @phase process-sources
 */
@Mojo(defaultPhase = LifecyclePhase.PACKAGE,name = "play")
public class MyMojo extends AbstractMojo
{

    @Parameter(readonly = true, defaultValue = "${project.packaging}")
    private String packing;

    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
    @Parameter(readonly = true, defaultValue = "${project.packaging}")
    private File outputDirectory;

    @Parameter(readonly = true, defaultValue = "${project.build.directory}/${project.build.finalName}.${project.packaging}")
    private File finalPackage;


    public void execute()
        throws MojoExecutionException
    {

        System.out.println(packing);
        System.out.println(outputDirectory);
        System.out.println(finalPackage);
        try {
            JarInputStream jarInputStream = new JarInputStream(new FileInputStream(finalPackage));
            JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(new File("d://asd.jar")));
            int i=0;
            while (true){
                JarEntry nextJarEntry = jarInputStream.getNextJarEntry();
                if(nextJarEntry==null){
                    break;
                }
                jarOutputStream.putNextEntry(nextJarEntry);
                IOUtil.copy(jarInputStream,jarOutputStream);
            }
            jarInputStream.close();
            jarOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
    }
}
