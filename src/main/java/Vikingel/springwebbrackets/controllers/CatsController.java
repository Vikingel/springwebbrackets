package Vikingel.springwebbrackets.controllers;

import Vikingel.springwebbrackets.entities.Cat;
import Vikingel.springwebbrackets.services.GetCatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@Controller
public class CatsController {
    @Autowired
    GetCatsService getCatsService;

    @GetMapping("/catalog")
    public String showCatalog(@RequestParam(name = "file", required = false) String file,
                               Model model)  {
          if (file!=null && !file.isEmpty()) {
            ArrayList<Cat> list = null;
                        try {
                list = getCatsService.readCats(file);
                model.addAttribute("list_of_cats", list);
                         }
            catch (FileNotFoundException e) {
                model.addAttribute("errorMsg", "такого файла нет");
            }
                    }
        return "catalog";
    }

}
