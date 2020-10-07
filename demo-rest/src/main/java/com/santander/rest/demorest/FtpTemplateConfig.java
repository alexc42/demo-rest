package com.santander.rest.demorest;




import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Configuration
class FtpTemplateConfig {

    // This is only a test (should be used in a controller with  an autowired "FtpRemoteFileTemplate"
    // I´m able to upload binary file to my ftp service in my azure account

    // This code is executed in start of springboot server, upload file to ftp server
    /*
    @Bean
    InitializingBean initializingBean(FtpRemoteFileTemplate template) {
        return () -> template.execute(session ->
        {
                    System.out.println("Upload Local File in FTP server (Azure):  **************");
                    File file = new File("/Users/javier.leyva", "US.wav");
                    FileInputStream fout = new FileInputStream(file);
                    session.write(fout,"US1.wav");
                    System.out.println("Write Remote File:  " + file.getAbsolutePath() + "*******");
                    return null;
                });
    }
    */

    //Test to check Pool connections FTP Server
    // Hay que descomentar este código para ver como funciona el pool d econexiones
    // En azure hay que abrir el puerto 20, es probable que lo cierre NSG
    /*
    @Bean
    InitializingBean initializingBean(DefaultFtpSessionFactory template) throws InterruptedException, FileNotFoundException {
    ExecutorService executor = Executors.newCachedThreadPool();
    final CachingSessionFactory factory = new CachingSessionFactory(template, 20);
    final Random random = new Random();
    final AtomicInteger failures = new AtomicInteger();



    for (int i = 0; i < 30; i++) {
        executor.execute(() -> {
            try {
                Session session = factory.getSession();
                System.out.println("Open Connection FTP Server:  ");
                Thread.sleep(10000);
                session.close();
                System.out.println("Close Connection FTP Server:  ");
            } catch (Exception e) {
                e.printStackTrace();
                failures.incrementAndGet();
            }
        });
    }
    executor.shutdown();
    executor.awaitTermination(10000, TimeUnit.SECONDS);
    System.out.println("End of Test Connectio Pool (FTP Server):  ");

    return null;

    }

*/


    @Bean
    DefaultFtpSessionFactory defaultFtpSessionFactory(
            @Value("jleyva") String username,
            @Value("Jleyva3001") String pw,
            @Value("javier12342.francecentral.cloudapp.azure.com") String host,
            @Value("21") int port) {
        DefaultFtpSessionFactory defaultFtpSessionFactory = new DefaultFtpSessionFactory();
        defaultFtpSessionFactory.setPassword(pw);
        defaultFtpSessionFactory.setUsername(username);
        defaultFtpSessionFactory.setHost(host);
        defaultFtpSessionFactory.setPort(port);

        return defaultFtpSessionFactory;
    }

    @Bean
    FtpRemoteFileTemplate ftpRemoteFileTemplate(DefaultFtpSessionFactory dsf) {
        return new FtpRemoteFileTemplate(dsf);
    }
}
/*
<bean id="cachingSessionFactory" class="org.springframework.integration.file.remote.session.CachingSessionFactory">
<constructor-arg ref="ftpSessionFactory"/>
<constructor-arg value="10"/>
<property name="sessionWaitTimeout" value="1000"/>
</bean>

 */