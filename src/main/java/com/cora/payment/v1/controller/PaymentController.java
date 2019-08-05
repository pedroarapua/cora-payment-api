package com.cora.payment.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cora.payment.v1.dto.PaymentDto;

@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

	/*
	 * private final PaymentBusiness paymentBusiness; private final PaymentMapper
	 * paymentMapper;
	 * 
	 * @Autowired public PaymentController(final PaymentBusiness paymentBusiness,
	 * final PaymentMapper paymentMapper) { this.paymentBusiness = paymentBusiness;
	 * this.paymentMapper = paymentMapper; }
	 */
  @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public PaymentDto post(@RequestBody @Valid final PaymentDto paymentDto) {
    Payment payment = this.paymentMapper.deserialize(paymentDto)
    return buildResponse(HttpStatus.OK, paymentMapper.serialize(reasonBusiness.findAll()));
  }

}
