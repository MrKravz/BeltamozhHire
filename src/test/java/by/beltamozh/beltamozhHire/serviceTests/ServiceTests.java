package by.beltamozh.beltamozhHire.serviceTests;

import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ServiceTests {
    private final UserService userService;

    @Autowired
    public ServiceTests(UserService userService) {
        this.userService = userService;
    }

    @Test
    void isUserWithId1CanBeFound() {
        //given
        int id = 1;
        //when
        Optional<User> user = userService.findById(id);
        //then
        assert user.isPresent() == true : "userNotFound";
    }

    @Test
    void isAllCanBeFound() {
        //given
        //when
        Optional<List<User>> user = userService.findAll();
        //then
        assert user.isPresent() == true : "usersNotFound";
    }
}
