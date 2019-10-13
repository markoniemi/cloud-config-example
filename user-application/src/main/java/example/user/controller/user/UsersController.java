package example.user.controller.user;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import example.user.service.UserService;

@Controller
public class UsersController {
//    @Resource
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/users", method = RequestMethod.GET)
    public ModelAndView users() {
        ModelAndView model = new ModelAndView();
        model.setViewName("/user/users");
        model.addObject("users", userService.findAll());
        return model;
    }
}
