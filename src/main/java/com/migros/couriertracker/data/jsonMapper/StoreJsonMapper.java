package com.migros.couriertracker.data.jsonMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.migros.couriertracker.data.dto.Store;
import com.migros.couriertracker.util.ObjectMapperFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreJsonMapper {

	@Value("classpath:data/stores.json")
	Resource resourceFile;;

	private final ObjectMapper mapper = ObjectMapperFactory.instance;

	public StoreJsonMapper() {
	}

	public List<Store> getStores() {
		ArrayList<Store> result = null;

		try {
			result = mapper.readValue(resourceFile.getFile(),
					mapper.getTypeFactory().constructCollectionType(List.class, Store.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
