package myWork;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class TestZipArchive {

    ClassLoader cl = TestZipArchive.class.getClassLoader();

    @Test
    void zipParseTest() throws Exception {

        try (
                InputStream is = cl.getResourceAsStream("exampleArchive.zip");
                ZipInputStream zis = new ZipInputStream(is)
        ) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String entryName = entry.getName();
                if (entryName.contains(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("January");
                } else if (entryName.contains(".pdf")) {
                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Регламент управления процессом решения");
                } else if (entryName.contains(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(0)[0]).contains("Student");
                }
            }
        }
    }
}