// based on: http://www.oracle.com/technetwork/articles/java/compress-1565076.html

package com.dbbear.spike;

import java.io.*;
import java.util.zip.*;

public class ZipDemo {
	static final int BUFFER = 2048;

	public static void main(String[] args) {

		final String WORKING_DIRECTORY = "/Users/k0emt/tmp/";
		final String SOURCE_FILES_DIRECTORY = WORKING_DIRECTORY + "zip_source/";
		byte data[] = new byte[BUFFER];

		try {
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(WORKING_DIRECTORY + "my.zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
			//out.setMethod(ZipOutputStream.DEFLATED);

			File f = new File(SOURCE_FILES_DIRECTORY);
			String files[] = f.list();

			for (int i = 0; i < files.length; i++) {
				System.out.println("Adding: " + files[i]);
				FileInputStream fi = new FileInputStream(SOURCE_FILES_DIRECTORY + files[i]);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(files[i]);
				out.putNextEntry(entry);
				int count;
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
				}
				origin.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
