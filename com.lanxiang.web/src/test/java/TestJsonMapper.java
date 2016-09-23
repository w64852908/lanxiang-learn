import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanxiang.model.dto.AddressDTO;
import com.lanxiang.model.dto.UserDTO;
import com.lanxiang.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lanxiang on 16/9/2.
 */
public class TestJsonMapper {

    private final static Logger log = LoggerFactory.getLogger(TestJsonMapper.class);

    // AddressDTO
    private String addressId = "aid_23333";
    private String userId = "uid_23333";
    private String province = "河北省";
    private String city = "北京市";
    private String town = "回龙观镇";
    private String detail = "邮政研究院";

    // UserDTO
    private String name = "空条承太郎";
    private String password = "123456";
    private String sex = "男";
    private int age = 18;
    private int status;

    private AddressDTO addressDTO;
    private UserDTO userDTO;
    private String jsonData;

    private static ObjectMapper mapper = new ObjectMapper();

    @Before
    public void initAllDTO() {
        addressDTO = new AddressDTO(addressId, userId, province, city, town, detail);
        userDTO = new UserDTO(userId, name, password, sex, age, status, addressDTO);
    }

    @Before
    public void initJsons() {
        jsonData = "{\"id\":\"uid_23333\",\"name\":\"空条承太郎\",\"password\":\"123456\"," +
                "\"sex\":\"男\",\"age\":18,\"status\":0,\"addressDTO\":{\"id\":\"aid_23333\"," +
                "\"userId\":\"uid_23333\",\"province\":\"河北省\",\"city\":\"北京市\",\"town\":\"回龙观镇\"," +
                "\"detail\":\"邮政研究院\"}}";
    }

    @Test
    public void Object2Json() {
        String json = JsonUtil.convertObjectToString(userDTO);
        log.info("After convert object to json : {}", json);
    }

    @Test
    public void Json2Object() {
        UserDTO userDTO = (UserDTO) JsonUtil.convertStringToObject(jsonData, UserDTO.class);
        log.info("After convert json to object : {}", userDTO.toString());
    }
}
