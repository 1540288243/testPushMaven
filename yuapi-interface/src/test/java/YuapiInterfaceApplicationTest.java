import com.yupi.YuapiInterfaceApplication;
import com.yupi.yuapiInterfacesdk.client.YuApiClient;
import com.yupi.yuapiInterfacesdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = YuapiInterfaceApplication.class)
public class YuapiInterfaceApplicationTest {

    @Resource
    private YuApiClient yuApiClient;

    @Test
    void test() {
        User user = new User();
        user.setUserName("yangxia");
        String result = yuApiClient.getUsernameByPost(user);
        System.out.println(result);
    }
}
