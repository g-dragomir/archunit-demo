package com.demo.payment.rest;

import com.demo.payment.domain.PaymentItem;
import com.demo.payment.exception.PaymentNotFoundException;
import com.demo.payment.service.PaymentService;
import io.swagger.annotations.Api;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Payment API", tags = "Payment APIs")
@RestController
@RequestMapping(path = "payments",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentResource.class);

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/live", method = RequestMethod.GET)
    public ResponseEntity live() {
        return ResponseEntity.ok(DateTime.now().toString());
    }

    @RequestMapping(value = "/{paymentId}", method = RequestMethod.GET)
    public ResponseEntity getPaymentById(@PathVariable("paymentId") int paymentId) {

        try {
            PaymentItem paymentItem = paymentService.getPaymentById(paymentId);

            return ResponseEntity.ok().body(paymentItem);

        } catch (PaymentNotFoundException paymentEx) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (Exception ex) {
            LOGGER.error("Error getting payment with id: " + paymentId, ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public ResponseEntity getAllPaymentsForCustomer(@PathVariable("customerId") int customerId) {

        try {
            List<PaymentItem> paymentItems = paymentService.getAllPaymentsForCustomer(customerId);

            return ResponseEntity.ok().body(paymentItems);

        } catch (Exception ex) {
            LOGGER.error("Error getting all the payments for the customerId: " + customerId, ex);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // other APIs ...

}