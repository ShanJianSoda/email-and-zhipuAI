package cn.chen.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.deserialize.MessageDeserializeFactory;
import com.zhipu.oapi.service.v4.model.*;
import okhttp3.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhang
 * @date 2024/12/12 14:39
 */
public class ZhiPuAPIUtil {
    private final static Logger logger = LoggerFactory.getLogger(ZhiPuAPIUtil.class);
    // api key
    private static final String API_SECRET_KEY = "xxxxxxxx";
    // 业务id
    private static final String requestIdTemplate = "write-letter";

    private static final ClientV4 client = new ClientV4.Builder(API_SECRET_KEY)
            .networkConfig(300, 100, 100, 100, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(8, 1, TimeUnit.SECONDS))
            .build();
    private static final ObjectMapper mapper = MessageDeserializeFactory.defaultObjectMapper();

    public static String sendMsg(String message) throws JsonProcessingException {
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), message);
        messages.add(chatMessage);
        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model("glm-4-flash") // Constants.ModelChatGLM4Flash 还没更新上去
                .stream(Boolean.FALSE)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .requestId(requestId)
//                .tools(chatToolList)
//                .toolChoice("auto")
                .build();

        ModelApiResponse sseModelApiResp = client.invokeModelApi(chatCompletionRequest);
        logger.info("model output: {}", mapper.writeValueAsString(sseModelApiResp));
        return (String)sseModelApiResp.getData().getChoices().get(0).getMessage().getContent();
    }
}
