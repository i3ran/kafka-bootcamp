package com.course.avro.tutorial;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import com.course.avro.data.Hello;

public class HelloAvro {

	public static void main(String[] args) {
//		var data = new Hello();
//		data.setMyIntField(ThreadLocalRandom.current().nextInt());
//		data.setMyStringField("The time is " + LocalTime.now());

		var data = Hello.newBuilder().setMyIntField(ThreadLocalRandom.current().nextInt())
				.setMyStringField("The time is " + LocalTime.now()).build();

		var data2 = Hello.newBuilder().setMyIntField(ThreadLocalRandom.current().nextInt())
				.setMyStringField("The time is " + LocalTime.now()).build();

		var datumWriter = new SpecificDatumWriter<>(Hello.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			var file = new File("helloAvro.avro");
			dataWriter.create(data.getSchema(), file);
			for (int i = 0; i < 10; i++) {
				var d = Hello.newBuilder().setMyIntField(ThreadLocalRandom.current().nextInt())
						.setMyStringField("The time is " + LocalTime.now()).build();
				dataWriter.append(d);
				System.out.println("Written : " + d);
				try {
				  Thread.sleep(100); // sleep for 1000 milliseconds which is 1 second
				} catch (InterruptedException e) {
				  e.printStackTrace();
				}

			}
			/*for (int i = 0; i < 10; i++) {
				var d = Hello.newBuilder()
						.setMyIntField(ThreadLocalRandom.current().nextInt())
						.setMyStringField("ddddd")
						.setMyEpoch(String.valueOf(new Date().getTime()))
						.build();
				dataWriter.append(d);
				System.out.println("Written : " + d);
				try {
					Thread.sleep(100); // sleep for 1000 milliseconds which is 1 second
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}*/
//			dataWriter.append(data);
//			dataWriter.append(data2);

//			System.out.println("Written : " + data);
//			System.out.println("Written : " + data2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
