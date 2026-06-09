package cn.xuele.activity;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 拼团活动服务启动类。
 *
 * @author XueLe
 * @version 1.0.0
 * @since 2026/06/09 10:33
 */
@SpringBootApplication(scanBasePackages = "cn.xuele.activity")
@EnableDubbo
public class ActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class, args);
    }
}
