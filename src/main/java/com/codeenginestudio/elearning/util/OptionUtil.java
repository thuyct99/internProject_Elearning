package com.codeenginestudio.elearning.util;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import com.codeenginestudio.elearning.dto.OptionDTO;
public class OptionUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String parseToJson (List<OptionDTO> allOptions) throws JsonProcessingException {
		
		String newStringJSON = mapper.writeValueAsString(allOptions);
		
		return newStringJSON;
	}
	
	public static List<OptionDTO> parseToObject(String jsongString) {
		List<OptionDTO> options = new ArrayList<>();
		
		try {
			options = mapper.readValue(jsongString, mapper.getTypeFactory().constructCollectionType(List.class, OptionDTO.class));
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		return options;
	}
}
