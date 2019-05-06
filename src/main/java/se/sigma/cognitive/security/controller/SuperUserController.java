package se.sigma.cognitive.security.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.sigma.cognitive.security.requestModel.UserDetailsRequestModel;
import se.sigma.cognitive.security.response.UserRest;
import se.sigma.cognitive.security.service.UserService;
import se.sigma.cognitive.security.sharedDto.UserDto;

@RestController
@RequestMapping("/users")           //http://localhost:8080/super/dashboard
public class SuperUserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id) {

        UserRest userRest = new UserRest();

        UserDto userDto =  userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, userRest);
        return userRest;
    }


    @PostMapping
    public UserRest addUser(@RequestBody UserDetailsRequestModel user) {
        UserRest returnValue= new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);


        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }


    @GetMapping
    public String updateUser() {
        return "Get s called";
    }


    @DeleteMapping
    public String deleteUser() {
        return "deleteUser called";
    }


}
