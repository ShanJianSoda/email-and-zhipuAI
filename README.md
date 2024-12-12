调用智谱清言的AI接口生成内容并发送邮件骚扰朋友

使用：

1. 需要一个质谱清言的API Key
申请：https://bigmodel.cn/usercenter/proj-mgmt/apikeys

2. 需要邮箱和邮箱验证码
   这里以网易邮箱为例，我们需要用自己的邮箱作为发送邮箱，就需要开启POP3/SMTP/IMAP。
   登录邮箱–设置–账户–开启POP3/SMTP/IMAP，开启时可能会有短信验证，开启后显示验证码之类的一串英文，复制保存起来，后面要用
   
3. 需要骚扰的对象邮箱和邮件内容，以及发送时间，频率，在SendEmailTimer文件里修改

4. 在MailUtil里添加你的邮箱和验证码（因为@Resoure不让用在static上，姑且用硬编码吧

5. 在ZhiPuAPIUtil里添加质谱清言的API Key

6. 运行 

tip：
按理来说可以更简单的实现但是我为了方便用了springboot
