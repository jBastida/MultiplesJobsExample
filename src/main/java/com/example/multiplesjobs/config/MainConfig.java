package com.example.multiplesjobs.config;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.multiplesjobs.jobs.Job1Config;
import com.example.multiplesjobs.jobs.Job2Config;

@Configuration //Con poner 1 etiqueta configuración sobra, con varias sigue funcionando igualmente pero tiene más sentido bajo mi criterio hacerlo así
@Import({Job1Config.class, Job2Config.class}) // Para añadir clases que creen beans en spring es necesaria ponerlas en el import y no es necesario ponerles a esta ninguna etiqueta de spring
public class MainConfig {
	
	private static final String EACH_TEN_SECONDS = "*/10 * * ? * *";
	
	@Autowired
	JobLauncher jobLauncher; //Es necesario para poder ejecutar los jobs de forma continuada
	
	@Autowired
	Job job1, job2;//Declaramos los jobs con el mismo nombre que en los métodos, en este caso se han definido en Job1Config y Job2Config
	
	//Método para evitar repetir código si lo que queremos es que se ejecute el job que tenemos definido N veces
	private JobParameters generateJobParameters() {
		return new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
	}
	
	@Scheduled(cron = EACH_TEN_SECONDS)//Se puede usar una constante
	public void executeJob1() throws Exception {
		jobLauncher.run(job1, generateJobParameters());//Ejecutamos el job1
	}
	
	@Scheduled(cron = "*/15 * * ? * *")//Se puede poner hardcodeado
	public void executeJob2() throws Exception {
		jobLauncher.run(job2, generateJobParameters());//Ejecutamos el job2
	}
	
}
