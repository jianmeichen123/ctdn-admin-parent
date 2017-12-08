package com.gi.ctdn;

import com.gi.ctdn.config.FMSWebConfiguration;
import com.gi.ctdn.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({FMSWebConfiguration.class,SwaggerConfiguration.class})
public class FMSApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(FMSApplication.class, args);
    }
}
