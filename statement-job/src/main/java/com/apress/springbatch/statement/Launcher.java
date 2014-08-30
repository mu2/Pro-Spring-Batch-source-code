package com.apress.springbatch.statement;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Muthukumar on 8/24/2014 5:22 PM in ${PACKAGE_NAME} for ${PROJECT}.
 */
public class Launcher {


    public static void main (String args[])       {
        System.out.println("Om Vignarajaya Namaha!");
        String[] springConfig  =
                {
                        "jobs/statementJob.xml"
                };

        ApplicationContext context =
                new ClassPathXmlApplicationContext(springConfig);

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("statementJob");

        try {

            JobExecution execution = jobLauncher.run(job, new JobParametersBuilder().addString("RunID","d433").toJobParameters());
            System.out.println("Exit Status : " + execution.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }

}
