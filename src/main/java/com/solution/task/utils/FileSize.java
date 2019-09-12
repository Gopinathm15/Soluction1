package com.solution.task.utils;

import com.solution.task.exceptions.BadRequestHttpException;

public class FileSize {

	private static Long currentDataSize = 0L;

	public static void addDataSize(String data) {
		currentDataSize = currentDataSize + data.getBytes().length;
		long dataInMB = currentDataSize / (1024L * 1024L);
		if (dataInMB > 1024) {
			throw new BadRequestHttpException("4010");
		}
	}

	public static void removeDataSize(String data) {
		currentDataSize = currentDataSize - data.getBytes().length;
	}

}
