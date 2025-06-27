package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import umc.spring.service.UserService.UserCommandService;
import umc.spring.web.dto.users.UserRequestDTO;

@Controller
@RequiredArgsConstructor
public class MemberViewController {

    private final UserCommandService userCommandService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userJoinDto", new UserRequestDTO.JoinDto());
        return "signup";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @PostMapping("/users/signup")
    public String joinUsers(@ModelAttribute("userJoinDto") UserRequestDTO.JoinDto request,
                            BindingResult bindingResult, Model model
    ) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }
        try{
            userCommandService.joinUser(request);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }

    }
}
