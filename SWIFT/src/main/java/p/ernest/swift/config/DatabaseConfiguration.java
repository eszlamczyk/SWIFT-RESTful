package p.ernest.swift.config;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import p.ernest.swift.service.BankService;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Profile("!test")
@Configuration
public class DatabaseConfiguration {


    private final ResourceLoader resourceLoader;

    public DatabaseConfiguration(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @Bean
    CommandLineRunner initData(
            BankService bankService
    ){
        return args -> {
            if (bankService.getAmountOfBanks() < 1){
                Resource resource = resourceLoader.getResource("classpath:banks.csv");
                try (
                        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
                        CSVReader reader = new CSVReader(inputStreamReader)
                ) {

                    reader.skip(1);

                    List<String[]> records = reader.readAll();
                    List<String[]> deferredRecords = new ArrayList<>();

                    for (String[] record : records) {
                        if (record.length == 8) {
                            String countryIso2Code = record[0];
                            String swiftCode = record[1];
                            String codeType = record[2];
                            String name = record[3];
                            String address = record[4];
                            String townName = record[5];
                            String countryName = record[6];
                            String timeZone = record[7];

                            if (swiftCode.endsWith("XXX")) {
                                bankService.addNewBank(
                                        swiftCode,
                                        countryIso2Code,
                                        codeType,
                                        name,
                                        address,
                                        townName,
                                        countryName,
                                        timeZone);
                            } else {
                                deferredRecords.add(record);
                            }
                        } else {
                            System.out.println("Invalid record format: " + String.join(",", record));
                        }
                    }
                    for (String[] deferredRecord : deferredRecords) {
                        String countryIso2Code = deferredRecord[0];
                        String swiftCode = deferredRecord[1];
                        String codeType = deferredRecord[2];
                        String name = deferredRecord[3];
                        String address = deferredRecord[4];
                        String townName = deferredRecord[5];
                        String countryName = deferredRecord[6];
                        String timeZone = deferredRecord[7];

                        bankService.addNewBank(
                                swiftCode,
                                countryIso2Code,
                                codeType,
                                name,
                                address,
                                townName,
                                countryName,
                                timeZone);
                    }
                } catch (IOException | CsvException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
