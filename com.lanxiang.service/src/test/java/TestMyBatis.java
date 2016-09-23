import com.google.inject.*;
import com.lanxiang.guice.ServiceModule;
import com.lanxiang.model.User;
import com.lanxiang.model.dto.AddressDTO;
import com.lanxiang.model.dto.UserDTO;
import com.lanxiang.service.UserService;
import com.lanxiang.util.UuidUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by lanxiang on 16/9/1.
 */
public class TestMyBatis {

    @Inject
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(TestMyBatis.class);

    @Before
    public void init() throws Exception {
        Injector injector = Guice.createInjector(new ServiceModule());

        userService = injector.getInstance(UserService.class);
    }

    @Test
    public void testInsert() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("soldier 76");
        userDTO.setAge(40);
        userDTO.setPassword("2333333333");
        userDTO.setSex("male");
        userDTO.setStatus(0);
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId("aid_" + UuidUtil.getUuid());
        addressDTO.setCity("yoyo");
        addressDTO.setProvince("coco");
        addressDTO.setTown("jojo");
        addressDTO.setDetail("lolo");
        userDTO.setAddressDTO(addressDTO);
        userService.createUser(userDTO);
    }

    @Test
    public void findUser() {
        String username = "soldier 76";
        User user = userService.findUserByName(username);
        log.info("User detail info : {}", user.toString());
    }
}
