package sandbox

import com.vaadin.server.VaadinServlet
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.embedded.ServletRegistrationBean
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

import javax.servlet.ServletContext
import javax.servlet.ServletException

@Configuration
@EnableAutoConfiguration
@ComponentScan(value = "sandbox")
class Application extends SpringBootServletInitializer{

    @Override
    void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("productionMode", "false")
        super.onStartup(servletContext)
    }

    @Bean
    public ServletRegistrationBean vaadinServlet(){
        ServletRegistrationBean registration = new ServletRegistrationBean(new VaadinServlet(), "/*");

        Map<String, String> params = new HashMap<String, String>();
        params.put("UI", "sandbox.AddressbookUI");
        registration.setInitParameters(params);
        return registration;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application, args);
    }
}
