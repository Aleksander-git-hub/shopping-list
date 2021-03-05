package com.home.shoppinglist.controllers;

import com.home.shoppinglist.entity.ShoppingItem;
import com.home.shoppinglist.repository.ShoppingItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShoppingListController {

    private final ShoppingItemRepository repository;

    @Autowired
    public ShoppingListController(ShoppingItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String indexPage(Model model) {
        model.addAttribute("items", repository.findAll());
        model.addAttribute("item", new ShoppingItem());
        return "index";
    }

    @PostMapping
    public String newShoppingItem(ShoppingItem shoppingItem) {
        repository.save(shoppingItem);
        // обновляем содержимое таблицы после того как была добавлена новая запись
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteShoppingItem(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
