package com.example.controller.manager;

import com.example.entities.Order;
import com.example.entities.views.EmployeeReport;
import com.example.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * ManagerOrderController consists of api endpoints that Managers have access too
 */
@RestController
@RequestMapping("/api/manager/order")
public class ManagerOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerOrderController.class);

    @Autowired
    OrderService service;

    //region Get Mappings

    /**
     * Represents the get mapping for baseUrl/api/manager/order/show, this url will return orders
     *
     *
     * @return List<Order> representing all Orders in the database
     *
     **/
    @GetMapping(value= {"/show/{id}", "/show"})
    @ResponseBody
    public List<Order> getOrders(@PathVariable(required = false) Optional<Integer> id, @RequestParam(required = false) Optional<Boolean> completed) {

        return service.getOrders(id, completed);
    }

    /**
     *
     * @param body representing the request body containing start and end date to generate the report
     * @return
     */
    @GetMapping("/employeeReport")
    @ResponseBody
    public Optional<List<EmployeeReport>> employeeReports(@RequestBody Map<String, Date> body) {
        try {

//            if(start.toLocalDate().isSupported(ChronoField.DAY_OF_MONTH))
            return service.getProfitReportByEmployee(body.get("start"), body.get("end"));

        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public Optional<Order> getById(@PathVariable int id) {
        return service.getOrderById(id);
    }

//    @GetMapping("/showByDay")
//    @ResponseBody
//    public Optional<List<Order>> receivedOrdersByDay(@RequestBody Map<String, String> start) {
//        try {
////            if(start.toLocalDate().isSupported(ChronoField.DAY_OF_MONTH))
//            return service.receivedOrdersByDay(start.get("date"));
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return Optional.empty();
//    }
    //endregion


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> insert(@RequestBody Order or) {
        //Check if there is information pass in from the post request
        //if yes, call insert service to do the insertion
        try {
            if (or != null) {
                return service.insert(or);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> update(@RequestBody Order or, @PathVariable int id) {
        //Check if there is information pass in from the post request
        //if yes, call update service to update the information
        try {
            if (or != null)
                return service.update(or, id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable int id) {
        return service.delete(id);
    }

    @DeleteMapping("/deleteAll")
    @ResponseBody
    public ResponseEntity<String> deleteAll() {
        return service.deleteAll();
    }
}
