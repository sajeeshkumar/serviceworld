/**
 * 
 */
package net.sajeesh.samplerest.api.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Service world application configuration object
 * @author Sajeesh
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.sajeesh.samplerest.api")
public class ServiceWorldConfiguration {

}
