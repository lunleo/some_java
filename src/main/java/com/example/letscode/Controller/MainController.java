package com.example.letscode.Controller;

import com.example.letscode.Domain.ItemEntity;
import com.example.letscode.Domain.User;
import com.example.letscode.Service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;

@Controller

@RequiredArgsConstructor

public class MainController {

    @Value("${upload.path}")
    private String uploadPath;


    private final MainService mainService;


    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }

    @GetMapping("/main")
    public String showIndex(Model model,@AuthenticationPrincipal User user){
        mainService.getInfoForIndex(model, user);
        return "index";
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam(name ="id")  String id){
        mainService.deleteItem(id);
        return "redirect:/main";
    }

    @PostMapping("/buy")
    public String buy( @RequestParam(name ="id")  String id){
        mainService.buyItem(id);
        return "redirect:/main";
    }



    @GetMapping("/add/new")
    public String addItem(ItemEntity itemEntity){
        return "add";
    }
    @PostMapping("/add/new")
    public String postItem(@Valid ItemEntity itemEntity, BindingResult bindingResult, @AuthenticationPrincipal User user, Model model) throws IOException {

        Boolean isSuccess = mainService.postItem(itemEntity, bindingResult, user, model);

        if(!isSuccess){
            return "add";
        }else{
            return "redirect:/main";
        }
    }

    @GetMapping("/addPrice")
    public String addPrice( @RequestParam(name ="id")  String id,Model model){
        model.addAttribute("itemId", id);
        return "add_price";
    }
    @PostMapping("/addPrice")
    public String postAddPrice(@RequestParam(name ="addValue")  String addValue, @RequestParam(name ="id")  String id, Model model) throws IOException {

        Boolean isSuccess = mainService.postAddPrice(addValue, id, model);

        if(!isSuccess){
            return "add_price";
        }else{
            return "redirect:/main";
        }

    }

}
