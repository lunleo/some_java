package com.example.letscode.Controller;

import com.example.letscode.Domain.Dto.AddPriceRequestDto;
import com.example.letscode.Domain.ItemEntity;
import com.example.letscode.Domain.User;
import com.example.letscode.Repository.ItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    public ItemEntityRepository itemEntityRepository;

    @GetMapping("/")
    public String greeting(){
        return "greeting";
    }


    @GetMapping("/add")
    public String addItem(ItemEntity itemEntity){
        return "add";
    }
    @PostMapping("/add")
    public String postItem(@Valid ItemEntity itemEntity, BindingResult bindingResult, @AuthenticationPrincipal User user, Model model) throws IOException {
        itemEntity.setOwner(user);

        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", itemEntity);
            return "add";
        }else{
            model.addAttribute("message",null);
            itemEntity.setPrice(itemEntity.getStartPrice());
            itemEntityRepository.save(itemEntity);
        }
        return "redirect:/main";
    }



}
