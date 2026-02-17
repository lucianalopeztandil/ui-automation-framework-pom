package data;

import io.qameta.allure.internal.shadowed.jackson.core.type.TypeReference;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        // 1. Leer el archivo a String usando FileUtils (con el charset correcto)
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {});

        return data;
    }

    @DataProvider
    public Object[][] getData(Method method) throws IOException {

        String fileName = method.getName();
        String filePath = System.getProperty("user.dir")
                + "//src//test//java//data//"
                + fileName + ".json";

        List<HashMap<String, String>> data = getJsonDataToMap(filePath);

        Object[][] objData = new Object[data.size()][1];

        for (int i = 0; i < data.size(); i++) {
            objData[i][0] = data.get(i);
        }

        return objData;
    }





}
