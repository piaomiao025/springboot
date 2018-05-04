package springboot.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该类为springboot 启动类
 * Created by zhangzihao on 2016/6/8.
 */
@SpringBootApplication
@RestController
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("start finsh !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

        //TODO 自定义 springboot 启动端口
        container.setPort(8080);
        System.out.println("start success ");
    }
}
