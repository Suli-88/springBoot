package com.sul.userRegistrationSystem.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sul.userRegistrationSystem.dto.CustomErrorType;
import com.sul.userRegistrationSystem.dto.UserDTO;
import com.sul.userRegistrationSystem.repository.UserJpaRepository;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);

	private UserJpaRepository userJpaRepository;

	@GetMapping("/list")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userJpaRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user) {
		if (userJpaRepository.getUserByName(user.getName()) != null) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType(
							"Unable to create new user. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userJpaRepository.save(user);
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {
		UserDTO user = userJpaRepository.getUserById(id);
		if (user == null) {
			return new ResponseEntity<UserDTO>(new CustomErrorType("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUse(@PathVariable("id") final Long id) {
		UserDTO user = userJpaRepository.getUserById(id);
		if (user == null) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Unable to delete. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		userJpaRepository.deleteById(id);
		return new ResponseEntity<UserDTO>(HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> putUser(@PathVariable("id") final Long id, @RequestBody final UserDTO user) {
		UserDTO currentUser = userJpaRepository.getUserById(id);
		if (currentUser == null) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Unable to upate. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		String name = user.getName();
		String email = user.getEmail();
		String address = user.getAddress();
		currentUser.setName(name);
		currentUser.setEmail(email);
		currentUser.setAddress(address);
		userJpaRepository.saveAndFlush(currentUser);
		return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
	}

	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}
}
