// based on: http://www.oracle.com/technetwork/articles/java/compress-1565076.html

package com.dbbear.spike;

import java.io.*;
import java.util.zip.*;

public class UnZipDemo {
	final static int BUFFER = 2048;

	public static void main(String argv[]) {
		try {
			final String WORKING_DIRECTORY = "/Users/k0emt/tmp/";

			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(WORKING_DIRECTORY + "/my.zip");
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;

			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting: " + entry);
				int count;
				byte data[] = new byte[BUFFER];

				// write the files to the disk
				FileOutputStream fos = new FileOutputStream(WORKING_DIRECTORY + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}