package sandbox
import com.vaadin.server.VaadinServlet
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

import javax.servlet.ServletContext
import javax.servlet.ServletException

@Configuration
@EnableAutoConfiguration
@ComponentScan
class Application extends SpringBootServletInitializer{

    /**
     * The first method, configure, is used to define this class as the configuration class of spring in a normal
     * web server. The second is used to achieve the same goal and also to make possible for spring to start an embedded
     * web server. It would be better if the main method wasn't necessary.
     */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application, args);
    }

    @Override
    void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext)
        servletContext.setInitParameter("productionMode", "false")
    }

    @Bean
    public ServletRegistrationBean vaadinServlet(){
        ServletRegistrationBean registration = new ServletRegistrationBean(new VaadinServlet(), "/*");

        Map<String, String> params = new HashMap<String, String>();
        params.put("UI", "sandbox.AddressbookUI");
        registration.setInitParameters(params);
        return registration;
    }
}
