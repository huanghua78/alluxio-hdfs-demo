package com.mycompany.app;


import alluxio.client.file.*;
import alluxio.AlluxioURI;
import alluxio.conf.AlluxioConfiguration;
import alluxio.conf.PropertyKey;

/**
 * Hello world!
 *
 */
public class AlluxioFS
{
    public static void main( String[] args ) throws
	    alluxio.exception.FileAlreadyExistsException,
	    alluxio.exception.InvalidPathException,
	    alluxio.exception.AlluxioException,
	    java.io.IOException
    {
	System.out.println("Starting write");
	FileSystem fs = FileSystem.Factory.get();
	AlluxioURI path = new AlluxioURI("/scratch/myFile2");
	FileOutStream out = fs.createFile(path);

	byte[] buffer = new byte[4096];
	for (int i = 0 ; i < 4096; i++)
		buffer[i] = (byte)('A' + i % 26);

	out.write(buffer);
	out.close();
	System.out.println("End write");
    }
}
