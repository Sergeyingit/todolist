package com.github.sergeyingit.todolist.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
@Aspect
public class loggingAspect {

    @Around("execution(void sendSimpleMailMessage(String, String, String))")
    public void sendEmailLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{


        File resourcesFolder = new ClassPathResource("/").getFile();
        File logFile = new File(resourcesFolder, "log.txt");
        if (!logFile.exists()) {
            logFile.createNewFile();
        }

        String purposeAddress = (String)proceedingJoinPoint.getArgs()[0];
        try {
            proceedingJoinPoint.proceed();


            try(FileWriter writer = new FileWriter(logFile,true)) {

                LocalDateTime localDateTime = LocalDateTime.now();
                String logMessage = localDateTime.toString() + ": trying to send an email to " + purposeAddress + "\n";
                writer.write(logMessage);
            }

        }
        catch (IOException e) {


            try(FileWriter writer = new FileWriter(logFile,true)) {

                LocalDateTime localDateTime = LocalDateTime.now();
                String logMessage = localDateTime.toString() + ": was thrown Exception when trying to send an email to " + purposeAddress + "-" + e.getMessage() + "\n\n";
                writer.write(logMessage);
            }
            throw e;
        }
        System.out.println("удалось отправить email по адресу "  + purposeAddress);


        try(FileWriter writer = new FileWriter(logFile,true)) {

            LocalDateTime localDateTime = LocalDateTime.now();
            String logMessage = localDateTime + ": email was sent successfully to" + purposeAddress + "\n\n";
            writer.write(logMessage);
        }

    }
}
