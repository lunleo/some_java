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
        Iterable<ItemEntity> messages = itemEntityRepository.findByOwner(user);
        Iterable<ItemEntity> messagesAll = itemEntityRepository.findByIsBought(false);
        model.addAttribute("messagesAll", messagesAll);
        model.addAttribute("messages", messages);
        return "index";
    }

    @PostMapping("/delete")
    public String deleteItem(@RequestParam(name ="id")  String id){
        itemEntityRepository.deleteById(Long.parseLong(id));
        return "redirect:/main";
    }

    @PostMapping("/buy")
    public String buy( @RequestParam(name ="id")  String id){
        ItemEntity itemEntity = itemEntityRepository.findById(Long.parseLong(id)).get();
        itemEntity.setIsBought(true);
        itemEntity.setFinishPrice(itemEntity.getPrice());
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


}
