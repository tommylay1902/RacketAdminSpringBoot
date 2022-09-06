package com.example.controller.employee;

import com.example.entities.Order;
import com.example.entities.User;
import com.example.security.UserPrincipal;
import com.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Employee order controller consists of api endpoints that employees have access too
 */
@RestController
@RequestMapping("/api/employee/order")
public class EmployeeOrderController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeOrderController.class);

    @Autowired
    OrderService service;


    @GetMapping("/show")
    @ResponseBody
    public List<Order> getRacketAdmin(@AuthenticationPrincipal UserPrincipal user, @RequestParam(required = false) Optional<Boolean> completed) {
        return service.getOrders(Optional.of(user.getAppUser().getId()), completed);
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> insert(@AuthenticationPrincipal UserPrincipal user, @RequestBody Order or) {
        //Check if there is information pass in from the post request
        //if yes, call insert service to do the insertion
        try {
            if (or != null) {
                or.setUser(user.getAppUser());
                return service.insert(or);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> update(@AuthenticationPrincipal UserPrincipal user, @RequestBody Order or, @PathVariable int id) {
        //Check if there is information pass in from the post request
        //if yes, call update service to update the information
        try {
            if (or != null) {
                User appUser = user.getAppUser();
                List<Order> lOrder = service.getOrders(Optional.of(appUser.getId()), null);

                final boolean[] isPresent = {false};
                lOrder.forEach(x -> {
                    if (x.getId() == id) {
                        isPresent[0] = true;
                        return;

                    }
                });
                if (isPresent[0] == true) {
                    or.setUser(appUser);
                    return service.update(or, id);
                } else {
                    throw new Exception("Not found");
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
