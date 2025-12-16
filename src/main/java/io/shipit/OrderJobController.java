package io.shipit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderJobController {
    private static final Logger logger = LoggerFactory.getLogger(OrderJobController.class);

    private final OrderJobEngineService orderJobEngineService;

    public OrderJobController(OrderJobEngineService orderJobEngineService) {
        this.orderJobEngineService = orderJobEngineService;
    }

    @GetMapping("/process-job")
    private ResponseEntity<Void> orderJob() {
        orderJobEngineService.processOrderJob("Mom & Pop Shom", "PROCESS_ORDER");

        return ResponseEntity.ok().build();
    }
}
