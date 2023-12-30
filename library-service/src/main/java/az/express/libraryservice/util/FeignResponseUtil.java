package az.express.libraryservice.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

public class FeignResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convertResponse(Response response, Class<T> beanClass) throws Exception {
        try {
            String feignResponseAsString = StreamUtils.copyToString(response.body().asInputStream(), StandardCharsets.UTF_8);
            return objectMapper.readValue(feignResponseAsString, beanClass);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}