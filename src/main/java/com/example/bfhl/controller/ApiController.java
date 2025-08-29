package com.example.bfhl.controller;

import com.example.bfhl.model.RequestData;
import com.example.bfhl.model.ResponseData;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ApiController {

    @PostMapping("/bfhl")
    public ResponseData processData(@RequestBody RequestData request) {
        ResponseData response = new ResponseData();

        List<String> data = request.getData();
        List<String> evens = new ArrayList<>();
        List<String> odds = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specials = new ArrayList<>();
        int sum = 0;

        for (String val : data) {
            if (val.matches("^-?\\d+$")) { // check if number
                int num = Integer.parseInt(val);
                sum += num;
                if (num % 2 == 0) evens.add(val);
                else odds.add(val);
            } else if (val.matches("^[a-zA-Z]+$")) { // alphabets
                alphabets.add(val.toUpperCase());
            } else {
                specials.add(val);
            }
        }

        // Build concat string (reverse + alternating caps)
        StringBuilder sb = new StringBuilder();
        String allAlpha = alphabets.stream().collect(Collectors.joining(""));
        String reversed = new StringBuilder(allAlpha).reverse().toString();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) sb.append(Character.toUpperCase(c));
            else sb.append(Character.toLowerCase(c));
        }

        // Fill response (CHANGE DETAILS TO YOURS 👇)
        response.setIs_success(true);
        response.setUser_id("john_doe_17091999"); // replace with your name + DOB
        response.setEmail("john@xyz.com");
        response.setRoll_number("ABCD123");
        response.setOdd_numbers(odds);
        response.setEven_numbers(evens);
        response.setAlphabets(alphabets);
        response.setSpecial_characters(specials);
        response.setSum(String.valueOf(sum));
        response.setConcat_string(sb.toString());

        return response;
    }
}