package ru.mytyshi.esnp.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;
import ru.mytyshi.esnp.models.MainTable;

@Component
public class CsvFileGenerator {
    public void writeMainTableToCsv(List<MainTable> mainTables, Writer writer) {
        try {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
//            CSVPrinter printer = new CSVPrinter(new PrintWriter("esnp.csv", "ANSI"), CSVFormat.DEFAULT);
            for (MainTable mainTable : mainTables) {
                printer.printRecord(mainTable.getFullName(), mainTable.getFullName(), mainTable.getAppointment(),
                        mainTable.getAddress(), mainTable.getIp(), mainTable.getAnyconnect(), mainTable.getPhone(),
                        mainTable.getPersonWhoCreated(), mainTable.getWhenCreated(), mainTable.getPersonWhoChanged(),
                        mainTable.getWhenChanged(), mainTable.getNote());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

