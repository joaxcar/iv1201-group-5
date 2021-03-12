package se.kth.iv1201.recruitmentapplicationgroup5.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import se.kth.iv1201.recruitmentapplicationgroup5.model.Account;

@Component("userSecurity")
public class UserSecurity {
     public boolean hasUserId(Authentication authentication, int id) {
    	 Account account = (Account)authentication.getPrincipal();
    	 System.out.println(id);
    	 System.out.println(account.getId());
    	 return account.getId() == id;
     }
}
