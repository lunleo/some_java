package com.example.letscode.Service;

import com.example.letscode.Controller.ControllerUtils;
import com.example.letscode.Domain.Dto.AddPriceRequestDto;
import com.example.letscode.Domain.ItemEntity;
import com.example.letscode.Domain.User;
import com.example.letscode.Repository.ItemEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainService {

    private final ItemEntityRepository itemEntityRepository;


    public Boolean postItem(ItemEntity itemEntity, BindingResult bindingResult, User user, Model model) {
        itemEntity.setOwner(user);

        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrorsMap(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", itemEntity);
            return false;
        }else{
            model.addAttribute("message",null);
            itemEntity.setPrice(itemEntity.getStartPrice());
            itemEntityRepository.save(itemEntity);
            return true;
        }
    }

}
