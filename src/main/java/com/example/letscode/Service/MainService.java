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

    public void getInfoForIndex(Model model, User user) {
        Iterable<ItemEntity> messages = itemEntityRepository.findByOwner(user);
        Iterable<ItemEntity> messagesAll = itemEntityRepository.findByIsBought(false);
        model.addAttribute("messagesAll", messagesAll);
        model.addAttribute("messages", messages);
    }

    public void deleteItem(String id) {
        itemEntityRepository.deleteById(Long.parseLong(id));
    }

    public void buyItem(String id) {
        ItemEntity itemEntity = itemEntityRepository.findById(Long.parseLong(id)).get();
        itemEntity.setIsBought(true);
        itemEntity.setFinishPrice(itemEntity.getPrice());
        itemEntityRepository.save(itemEntity);
    }

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

    public Boolean postAddPrice(String addValue, String id, Model model) {
        try {
            ItemEntity itemEntity = itemEntityRepository.findById(Long.parseLong(id)).get();
            itemEntity.setPrice(itemEntity.getPrice() + Integer.parseInt(addValue));
            itemEntityRepository.save(itemEntity);
            model.addAttribute("message",null);
            return true;
        } catch (Exception e) {
            AddPriceRequestDto addPriceRequestDto = new AddPriceRequestDto(id, addValue);
            model.addAttribute("message", addPriceRequestDto);
            return false;

        }
    }

}
