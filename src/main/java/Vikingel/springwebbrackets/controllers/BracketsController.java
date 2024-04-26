package Vikingel.springwebbrackets.controllers;

import Vikingel.springwebbrackets.services.GetBracketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Controller
public class BracketsController {
    @Autowired
    GetBracketsService getBracketsService;

    @GetMapping("/catalog")
    public String showCatalog(@RequestParam(name = "file", required = false) String file,
                              Model model) throws FileNotFoundException {
        String s1 = "В списке файла след строки:";
        String s2 = "Проверка строк:";
        if (file != null && !file.isEmpty()) {
            ArrayList<String> list = null;
            try {
                list = getBracketsService.readCats(file);
                ArrayList<String> listOfChecks = getBracketsService.checkBrackets(list);
                model.addAttribute("caption1", s1);
                model.addAttribute("list_of_brackets", list);
                model.addAttribute("caption2", s2);
                model.addAttribute("check", listOfChecks);
            } catch (FileNotFoundException e) {
                model.addAttribute("errorMsg", "такого файла нет");
            }
        }
        return "catalog";
    }

}
