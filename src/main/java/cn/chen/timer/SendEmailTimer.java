package cn.chen.timer;

import cn.chen.util.MailUtil;
import cn.chen.util.ZhiPuAPIUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.mail.MessagingException;

import java.util.*;

/**
 * @author chenhang
 * @date 2024/12/12 15:04
 */
public class SendEmailTimer {
    public void timer() {
        Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17); // Set the hour to 11 AM
        calendar.set(Calendar.MINUTE, 29); // Set the minute to 0
        calendar.set(Calendar.SECOND, 0); // Set the second to 0

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    List<String> emailList = new ArrayList<>();
                    emailList.add("example@qq.com");


                    String prompt = """
                            帮我写一封信，开头是见字如晤，风格偏向于日本的村上春树，大概150字左右，
                            
                            例子（尽量达到这种文字中蕴含着淡淡的情感，不要照抄）：“我不太能理解，为什么他们说今晚月色很美是含蓄的表白。
                            直到我看到朝阳下江水漾起的片片金鳞、漆黑的夜空中不甘散去的橘黄云彩、亦或者是夜宵摊子上高谈阔论掺杂着串子被炭火炙烤出油香的烟火气息，都下意识拿出手机想跟你分享。
                            我想把自己觉得美丽的东西传递给你。今天下雨了。”
                            """;
                    String msg = ZhiPuAPIUtil.sendMsg(prompt);
//                    new MailUtil2().sendMail(toEmail, msg);
//                    MailUtil.SendEmil(toEmail, msg);

                    emailList.forEach(email -> {
                        try {
                            MailUtil.SendEmil(email, msg);
                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                        }
                    });
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        };

        timer.schedule(timerTask, calendar.getTime(), 24 * 60 * 60 * 1000); // Execute every day at 11:00 AM

    }
}
