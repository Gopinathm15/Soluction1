package com.solution.task.request.validations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.solution.task.exceptions.BadRequestHttpException;

@Component
public class RequestValidator {

	@Value("${maxDataSize}")
	private int defaultLiveTime;

	public void validateInputRequest(JsonObject jsonObject) {
		if (!jsonObject.has("id")) {
			throw new BadRequestHttpException("4009");
		}
		if (jsonObject.get("id").getAsString().length() != 32) {
			throw new BadRequestHttpException("4008");
		}

		if (!jsonObject.has("data")) {
			throw new BadRequestHttpException("4007");
		}

		long KB = 1024L;
		long dataSize = jsonObject.get("data").getAsJsonObject().toString().getBytes().length / KB;
		if (dataSize > defaultLiveTime) {
			throw new BadRequestHttpException("4006");
		}

	}
}
