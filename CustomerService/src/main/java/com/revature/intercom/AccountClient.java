package com.revature.intercom;

import java.io.Serializable;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.revature.models.Account;

@FeignClient(name = "account-service", fallback = AccountClientIimpl.class)
public interface AccountClient extends Serializable {

	@GetMapping("accounts/customer/{id}")
	public List<Account> getAccountsByCustomerId(@PathVariable("id") int id);
}
