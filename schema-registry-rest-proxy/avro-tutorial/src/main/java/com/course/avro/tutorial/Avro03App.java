package com.course.avro.tutorial;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.course.avro.data.Avro03;

public class Avro03App {

	public static void main(String[] args) {
		var file1 = new File("avro03.avro");
		var data1 = Avro03.newBuilder()
				.setMyMandatoryValue("This is the mandatory value")
				.setMyWeirdButPossibleValue(false).build();

		write(data1, file1);
		System.out.println("");
		read(file1);

		System.out.println("----------------------------------------------------------");

//		var file2 = new File("avro03-with-optional.avro");
		var data2 = Avro03.newBuilder()
				.setMyMandatoryValue("This is the mandatory value")
				.setMyOptionalValue("not null value")
				.setMyOptionalValueWithDefault("overriding default value")
				.setMyWeirdButPossibleValue(4982).build();

		write(data2, file1);
		write(data1, file1);
		writeMultiple(List.of(data1, data2), file1);
		System.out.println("");
		read(file1);
	}

	private static void write(Avro03 data, File toFile) {
		var datumWriter = new SpecificDatumWriter<>(Avro03.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(data.getSchema(), toFile);
			dataWriter.append(data);

			System.out.println("Writing " + toFile.getName());
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void read(File fromFile) {
		var datumReader = new SpecificDatumReader<>(Avro03.class);

		System.out.println("Reading " + fromFile.getName());
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeMultiple(List<Avro03> dataItems, File toFile) {
		var datumWriter = new SpecificDatumWriter<>(Avro03.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(dataItems.get(0).getSchema(), toFile);
			dataItems.forEach(data -> {
				try {
					dataWriter.append(data);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Writing " + toFile.getName());
				System.out.println(data);

			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
