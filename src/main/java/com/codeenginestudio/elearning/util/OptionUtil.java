package com.codeenginestudio.elearning.util;

import com.codeenginestudio.elearning.dto.OptionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OptionUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static String parseToJson(List<OptionDTO> allOptions) throws JsonProcessingException {

		String newStringJSON = mapper.writeValueAsString(allOptions);

		return newStringJSON;
	}

	public static List<OptionDTO> parseToObject(String jsonString) {
		List<OptionDTO> options = new ArrayList<>();

		try {

			options = mapper.readValue(jsonString,
					mapper.getTypeFactory().constructCollectionType(List.class, OptionDTO.class));
		} catch (IOException e) {

			e.printStackTrace();
		}

		return options;
	}
}
