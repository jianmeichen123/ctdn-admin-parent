package com.gi.ctdn;

import com.gi.ctdn.config.CtdnAdminWebConfiguration;
import com.gi.ctdn.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CtdnAdminWebConfiguration.class,SwaggerConfiguration.class})
public class CtdnAdminApplication
{
    public static void main( String[] args )
    {
    	SpringApplication.run(CtdnAdminApplication.class, args);
    }
}
