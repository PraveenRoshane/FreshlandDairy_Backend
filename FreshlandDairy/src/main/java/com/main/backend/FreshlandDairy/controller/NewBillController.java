package com.main.backend.FreshlandDairy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.main.backend.FreshlandDairy.entity.NewBill;
import com.main.backend.FreshlandDairy.exception.ResourceNotFoundException;
import com.main.backend.FreshlandDairy.repository.NewBillRepository;



@RestController
@CrossOrigin(origins="http://localhost:4200")
public class NewBillController {
	
	@Autowired
	private NewBillRepository newbillrep;
	
	
	//get all
	@GetMapping("/bill")
	public List<NewBill> getAllBill() {
		return newbillrep.findAll();
	}
	
	
	//create
	@PostMapping("/bill")
	public NewBill createBill(@RequestBody NewBill bill) {
		
		NewBill newbill= newbillrep.save(bill);
		
//		URI Uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(NewOrder.getTransactionID())
//				.toUri();
//		return ResponseEntity.created(Uri).build();
		
		return newbill;
	}
	
	//get bill by id
	@GetMapping("/bill/{id}")
	public NewBill getBill(@PathVariable Long id) {
		return newbillrep.findById(id).get();
	}
	
	
	//delete
	@DeleteMapping("/bill/{id}")
	public ResponseEntity<Void> deleteBill(@PathVariable Long id) {

		newbillrep.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	
	
	//update
	@PutMapping("/bill/{id}")
	public ResponseEntity<NewBill> updateBill(@PathVariable Long id, @RequestBody NewBill bill) {
		
		NewBill newbill = newbillrep.findById(id)
				.orElseThrow(( )-> new ResourceNotFoundException("bill not exist with id :" + id));
		
		newbill.setDate(bill.getDate());
		newbill.setcName(bill.getcName());
		
		NewBill bb = newbillrep.save(newbill);
		return ResponseEntity.ok(bb);
		
	}
	
	
}