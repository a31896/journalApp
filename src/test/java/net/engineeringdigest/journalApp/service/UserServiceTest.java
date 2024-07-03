package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,3,5",
            "1,1,3"
    })
    public void testSum(int a, int b, int expected) {
        //assertEquals(expected,a+b);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "vipul",
            "ghansham"
    })
    public void testFindByUserName(String userName) {

        assertEquals(4,3+1);
        User user = userRepository.findByUserName(userName);
        //assertNotNull(user);
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        //assertTrue(userService.saveNewUser(user));
    }


}
