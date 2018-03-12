package sample.data.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpaceRegistryUtil {
	private final String SUBDOMAIN_NAME = "polyglotSubdomainName";
	private final String STRUCTURE_NAME = "polyglotStructureName";
	private final String TENANT_ID = "tenantId";

	private <S> String getSpaceId(S entity) {

		String json = getJSONString(entity);
		Map<String, Object> requestMap = getMapObject(json);
		return getTenantToken(requestMap);
	}

	public static String getJSONString(Object object) {

		String jsonString = null;
		ObjectMapper objMapper = new ObjectMapper();
		try {
			jsonString = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonString;
	}

	public static Map<String, Object> getMapObject(String value) {

		Map<String, Object> persistenceDataMap = null;
		ObjectMapper objMapper = new ObjectMapper();
		objMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		objMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			persistenceDataMap = objMapper.readValue(value, LinkedHashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		}


		return persistenceDataMap;
	}

	private String getTenantToken(Map<String, Object> payloadMap) {
		return (String) payloadMap.get(TENANT_ID);
	}
}