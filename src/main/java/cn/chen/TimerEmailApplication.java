package cn.chen;

import cn.chen.timer.SendEmailTimer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenhang
 * @date 2024/12/11 11:42
 */
@SpringBootApplication
public class TimerEmailApplication {
    public static void main(String[] args) {
        System.out.println("短信骚扰，启动！！！");
        SpringApplication.run(TimerEmailApplication.class, args);

        new SendEmailTimer().timer();
    }
}