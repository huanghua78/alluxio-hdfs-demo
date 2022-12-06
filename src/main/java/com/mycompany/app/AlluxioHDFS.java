package com.mycompany.app;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


/**
 * Hello world!
 *
 */
public class AlluxioHDFS
{
    public static void main( String[] args ) throws
	    alluxio.exception.FileAlreadyExistsException,
	    alluxio.exception.InvalidPathException,
	    alluxio.exception.AlluxioException,
	    java.io.IOException
    {
	System.out.println("Starting write");

	org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
	conf.set("fs.alluxio.impl", "alluxio.hadoop.FileSystem");
	conf.set("fs.AbstractFileSystem.alluxio.impl", "alluxio.hadoop.AlluxioFileSystem");
	conf.set("fs.defaultFS", "alluxio://localhost:19998");

	org.apache.hadoop.fs.FileSystem fs = org.apache.hadoop.fs.FileSystem.get(conf);

	String filename = "/scratch/HelloHDFSFromJava103";

	org.apache.hadoop.fs.FSDataOutputStream out = fs.create(new org.apache.hadoop.fs.Path(filename));
	byte[] buffout = new byte[4096];
	for (int i = 0 ; i < 4096; i++)
		buffout[i] = (byte)('a' + i % 26);
	out.write(buffout);
	out.close();

	org.apache.hadoop.fs.FSDataInputStream in = fs.open(new org.apache.hadoop.fs.Path(filename));
	byte[] buffin = new byte[4096];
	in.read(buffin);
	in.close();
	System.out.println("Got: " + (new String(buffin)));


	System.out.println("End write");
    }
}
