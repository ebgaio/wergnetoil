package com.wergnet.wergnetoil.api.resource;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {

	// Return of logged user
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CUSTOMER') and #oauth2.hasScope('read')")
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
	
	// Return of logged user
	@GetMapping("/{details}")
	@PreAuthorize("hasAuthority('ROLE_SEARCH_CUSTOMER') and #oauth2.hasScope('read')")
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.toString();
    }
}
