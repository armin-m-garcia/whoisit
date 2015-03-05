package com.inpowered;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class Util {

	public static URI toURI(String sUri)
	{
		try {
			return new URI(sUri);
		} catch (URISyntaxException e) {
			return null;
		}
	}
	
	public static URL findInClasspath(String resource)
	{
		URL resourceURL = Util.class.getClassLoader().getResource(resource); // Util.class.getResource(resource);
				
//		dumpClasspath(Util.class.getClassLoader());
		
		return resourceURL;
	}

	public static void dumpClasspath(ClassLoader loader)
    {
        System.out.println("Classloader " + loader + ":");

        if (loader instanceof URLClassLoader)
        {
            URLClassLoader ucl = (URLClassLoader)loader;
            System.out.println("\t" + Arrays.toString(ucl.getURLs()));
        }
        else
            System.out.println("\t(cannot display components as not a URLClassLoader)");

        if (loader.getParent() != null)
            dumpClasspath(loader.getParent());
    }
}
