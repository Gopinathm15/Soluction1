package com.solution.task.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.LoggerFactory;

public class FileIOUtils {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(FileIOUtils.class);

	private static ClassLoader classLoader;

	public static String getFileContent(String fileName) {
		try {
			String contents = new String(Files.readAllBytes(Paths.get(fileName)));
			return contents;
		} catch (IOException ioException) {
			ioException.printStackTrace();
			return "";
		}
	}

	public static InputStream getFileContentAsStream(String fileName) {
		try {
			classLoader = FileIOUtils.class.getClassLoader();
			return classLoader.getResourceAsStream(fileName);
		} catch (Exception e) {
			LOGGER.info("IO Excpetion Occured");
			LOGGER.error("Error ", e);
		}
		return null;
	}

}