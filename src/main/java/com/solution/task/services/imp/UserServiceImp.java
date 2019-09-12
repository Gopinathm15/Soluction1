package com.solution.task.services.imp;

import java.text.ParseException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.solution.task.exceptions.BadRequestHttpException;
import com.solution.task.request.validations.RequestValidator;
import com.solution.task.response.AppBaseResponse;
import com.solution.task.services.UserService;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private RequestValidator requestValidator;

	@Autowired
	private FileService fileService;

	@Value("${defaultLiveTime}")
	private int defaultLiveTime;

	@Value("${defaultFilePath}")
	private String defaultFilePath;

	@Override
	public ResponseEntity saveData(String appBaseRequest) {
		JsonParser jsonParser = new JsonParser();
		JsonObject dataObject = jsonParser.parse(appBaseRequest).getAsJsonObject();
		requestValidator.validateInputRequest(dataObject);

		if (dataObject.has("filePath") && dataObject.get("filePath").toString().trim() != "") {
			defaultFilePath = dataObject.get("filePath").getAsString();
		}

		int expiry = dataObject.has("expiry") && dataObject.get("expiry").getAsInt() != 0
				? dataObject.get("expiry").getAsInt()
				: defaultLiveTime;

		if (!fileService.put(dataObject.get("data").getAsJsonObject(), dataObject.get("id").getAsString(),
				defaultFilePath, expiry)) {
			throw new BadRequestHttpException("4004");
		}
		return ResponseEntity.ok(new AppBaseResponse());
	}

	@Override
	public ResponseEntity findData(String id) throws ParseException {
		JsonObject jsonObject = fileService.get(defaultFilePath, id);
		AppBaseResponse appBaseResponse = new AppBaseResponse();
		HashMap<String, Object> responseData = new Gson().fromJson(jsonObject.toString(), HashMap.class);
		appBaseResponse.setData(responseData);
		return ResponseEntity.ok(appBaseResponse);
	}

	@Override
	public ResponseEntity deleteData(String id) {
		fileService.delete(defaultFilePath, id);
		return ResponseEntity.ok(new AppBaseResponse<>());
	}

}
