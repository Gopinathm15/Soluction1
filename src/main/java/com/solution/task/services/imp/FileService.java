package com.solution.task.services.imp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.solution.task.exceptions.BadRequestHttpException;
import com.solution.task.utils.DateUtils;
import com.solution.task.utils.FileIOUtils;
import com.solution.task.utils.FileSize;

@Service
public class FileService {

	public boolean put(JsonObject dataObject, String id, String filePath, int expiry) {
		if (isFileExist(filePath, id)) {
			throw new BadRequestHttpException("4001");
		}

		try (FileWriter fileWriter = new FileWriter(filePath + "/" + id)) {
			dataObject.addProperty("expiry", DateUtils.setExpireTimeForOtp(expiry));
			FileSize.addDataSize(dataObject.toString());
			fileWriter.write(dataObject.toString());
		} catch (IOException ioException) {
			ioException.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean isFileLocked(String filePath, String fileName) {
		File file = new File(getFullFilePath(filePath, fileName, "LOCK"));
		if (file.exists()) {
			return true;
		}
		return false;
	}

	private boolean isFileExist(String filePath, String fileName) {
		File file = new File(getFullFilePath(filePath, fileName, ""));
		if (file.exists()) {
			return true;
		}
		return false;
	}

	private boolean lockFile(String filePath, String fileName) {
		File file = new File(getFullFilePath(filePath, fileName, ""));
		File lockedFile = new File(getFullFilePath(filePath, fileName, "LOCK"));
		return file.renameTo(lockedFile);
	}

	private boolean unLockFile(String filePath, String fileName) {
		File lockedFile = new File(getFullFilePath(filePath, fileName, "LOCK"));
		File file = new File(getFullFilePath(filePath, fileName, ""));
		return lockedFile.renameTo(file);
	}

	public JsonObject get(String filePath, String id) throws ParseException {
		Lock lock = new ReentrantLock();

		if (isFileLocked(filePath, id)) {
			throw new BadRequestHttpException("4003");
		}

		if (!isFileExist(filePath, id)) {
			throw new BadRequestHttpException("4002");
		}

		checkKeyExpired(filePath, id);

		try {
			lock.lock();
			String jsonString = FileIOUtils.getFileContent(getFullFilePath(filePath, id, ""));
			JsonParser jsonParser = new JsonParser();
			lockFile(filePath, id);
			return jsonParser.parse(jsonString).getAsJsonObject();
		} catch (Exception e) {
			unLockFile(filePath, id);
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return null;

	}

	public boolean delete(String filePath, String id) {
		Lock lock = new ReentrantLock();

		if (isFileLocked(filePath, id)) {
			throw new BadRequestHttpException("4003");
		}

		if (!isFileExist(filePath, id)) {
			throw new BadRequestHttpException("4002");
		}

		try {
			lock.lock();
			File file = new File(getFullFilePath(filePath, id, ""));
			FileSize.removeDataSize(FileIOUtils.getFileContent(getFullFilePath(filePath, id, "")));
			return file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		return false;
	}

	private void checkKeyExpired(String filePath, String fileName) throws JsonSyntaxException, ParseException {
		String jsonString = FileIOUtils.getFileContent(getFullFilePath(filePath, fileName, ""));
		JsonParser jsonParser = new JsonParser();
		if (DateUtils.isExpired(jsonParser.parse(jsonString).getAsJsonObject().get("expiry").getAsString())) {
			throw new BadRequestHttpException("4005");
		}
	}

	private String getFullFilePath(String filePath, String fileName, String type) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(filePath);
		stringBuilder.append("/");
		stringBuilder.append(fileName);
		if (type.equalsIgnoreCase("LOCK")) {
			stringBuilder.append("-L");
		}
		return stringBuilder.toString();
	}

}
