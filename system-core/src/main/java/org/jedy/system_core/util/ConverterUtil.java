package org.jedy.system_core.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ConverterUtil {

    public static <T> T convertJsonStringToObject(String jsonString, Class<T> convertObjectClass) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        mapper.registerModule(javaTimeModule);
        return mapper.readValue(jsonString, convertObjectClass);
    }


    public static <T> List<T> convertJsonStringToObjectList(String jsonString, Class<T> convertObjectClass) throws IOException, ClassNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        mapper.registerModule(javaTimeModule);
        Class<T[]> arrayClass = (Class<T[]>) Class.forName("[L" + convertObjectClass.getName() + ";");
        T[] objects = mapper.readValue(jsonString, arrayClass);
        return Arrays.asList(objects);
    }

    public static String convertObjectToJson(Object obj) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        mapper.registerModule(javaTimeModule);
        return mapper.writeValueAsString(obj);
    }

}



//Object와Json 변환 : https://stackoverrun.com/ko/q/13077735
//배열Object를 Json으로 변환 : https://stackoverflow.com/questions/6349421/how-to-use-jackson-to-deserialise-an-array-of-objects
//json으로 변환시 LocalDate타입 변환에 관한 처리 : https://stackoverflow.com/questions/45863678/json-parse-error-can-not-construct-instance-of-java-time-localdate-no-string-a
//objectmapper는 쓰레드 세이프한가? : 쓰레드 세이프하지만 트래픽이 많을때 성능에 대한 이슈가 있는듯함. https://stackoverflow.com/questions/3907929/should-i-declare-jacksons-objectmapper-as-a-static-field
