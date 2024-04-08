package com.course.avro.tutorial;

import com.course.avro.practice.Email;
import com.course.avro.practice.EmailType;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmailAvro {
    public static void main(String[] args) {
        var file = new File("email.avro");
        write(file);
        read(file);

    }

    private static String getCurrentTimeStampAsString(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.toString();
    }

    private static void write(File file){
        var data = Email.newBuilder()
                .setEmailType(EmailType.COM)
                .setEmail("com@test.com")
                .setCreatedTs(LocalDate.of(1998, 10, 04))
                .setUpdatedTs(Instant.now())
                .build();
        var datumWriter = new SpecificDatumWriter<>(Email.class);
        try (var dataWriter = new DataFileWriter<>(datumWriter)) {
            dataWriter.create(data.getSchema(), file);
            dataWriter.append(data);
            dataWriter.append(Email.newBuilder()
                    .setEmail("pers@test.com")
                    .setCreatedTs(LocalDate.of(1988, 10, 04))
                    .setEmailType(EmailType.WRK)
                    .setUpdatedTs(Instant.now())
                    .build());
            dataWriter.append(Email.newBuilder()
                    .setEmail("oth@test.com")
                    .setEmailType(EmailType.OTH)
//                            .setCreatedTs(getCurrentTimeStampAsString())
                    .setCreatedTs(LocalDate.of(1970, 01, 02))
                    .setUpdatedTs(Instant.now())
                    .build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void read(File file){
        var datumReader = new SpecificDatumReader<>(Email.class);
        try (var dataReader = new DataFileReader<>(file, datumReader)) {
            dataReader.forEach(data -> {
                System.out.println("...........\t" + data);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
