//package com.example.nrs.controller.customController;
//
//import com.example.nrs.entity.Rating;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//@Controller
//@RequestMapping({ "/", "/index" })
//public class ratingController {
//    @GetMapping
//    public String main(Model model) {
//        model.addAttribute("rating", new Rating());
//        return "user/rate";
//    }
//
//    @PostMapping
//    public String save(Rating rating, Model model) {
//        model.addAttribute("rating", rating);
//        return "user/rate-saved";
//    }
//}
