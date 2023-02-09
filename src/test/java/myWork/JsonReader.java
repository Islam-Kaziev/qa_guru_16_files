package myWork;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

public class JsonReader {

    ClassLoader cl = JsonReader.class.getClassLoader();

        @Test
        void jsonParseTest() throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();
            try (
                    InputStream resource = cl.getResourceAsStream("file.json")
            ) {
                FileJson fileJson = objectMapper.readValue(resource, FileJson.class);
                Assertions.assertThat(fileJson.name).isEqualTo("Vasia Pupkin");
                Assertions.assertThat(fileJson.id).isEqualTo("1231231");
                Assertions.assertThat(fileJson.age).isEqualTo(34);
                Assertions.assertThat(fileJson.married).isFalse();
                Assertions.assertThat(fileJson.address.street).isEqualTo("12, Bomzh St.");
                Assertions.assertThat(fileJson.address.city).isEqualTo("Perdolinsk");
                Assertions.assertThat(fileJson.address.country).isEqualTo("Zhoplyandia");
            }
        }
    }

