package com.epam.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.beans.ConfirmationToken;
import com.epam.beans.Users;
import com.epam.repository.ConfirmationTokenRepository;
import com.epam.repository.UserRepository;
import com.epam.rest.webservice.client.RegisterRestClient;
import com.epam.rest.webservice.client.RestClientUtil;
import com.epam.service.EmailService;

@Controller
public class RegisterController extends RestClientUtil {
	protected static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	@Autowired
	Users user;
	@Autowired
	RegisterRestClient registerRestClient;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	EmailService emailService;

	@PostMapping("/registerUser")
	public ModelAndView submitRegistrationForm(@Validated Users userDetails) {
		String encrpytedPassword = new BCryptPasswordEncoder()
				.encode(userDetails.getPassword() != null ? userDetails.getPassword() : "");
		user.setUsername(userDetails.getUsername());
		user.setPassword(encrpytedPassword);
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setGender(userDetails.getGender());
		user.setAge(userDetails.getAge());
		user.setEmail(userDetails.getEmail());
		ModelAndView modelAndView = new ModelAndView();
		Optional<Users> userFound = userRepository.findByUsername(userDetails.getUsername());
		Optional<Users> userEmailFound = userRepository.findByEmail(userDetails.getEmail());
		if (userFound.isPresent() || userEmailFound.isPresent()) {
			modelAndView.addObject("errorMsg", "Username Or Email Already Taken");
			modelAndView.setViewName("register");
		} else {
			Users userSaved = registerRestClient.register(user);

			if (userSaved != null) {
				ConfirmationToken confirmationToken = new ConfirmationToken(userSaved);

				confirmationTokenRepository.save(confirmationToken);
				emailService.sendMail(userSaved.getEmail(), "Complete Registration!",
						"To confirm your account, please click here : ".concat(url).concat(port)
								.concat("/confirm-account?token=").concat(confirmationToken.getConfirmationTokenValue()));
				LOGGER.info("User Registered!!!!");
				modelAndView.addObject("emailId", user.getEmail());
				modelAndView.setViewName("successfulRegisteration");
			}
		}
		return modelAndView;
	}

	@GetMapping("/registerUser")
	public ModelAndView displayUserRegistrationForm() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}

	@GetMapping(value = "/confirm-account")
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationTokenValue(confirmationToken);

		if (token != null) {
			Optional<Users> userFound = userRepository.findByUsername(token.getUser().getUsername());
			if (userFound.isPresent()) {
				userFound.get().setEnabled(true);
				userRepository.save(userFound.get());
			}
			modelAndView.setViewName("accountVerified");
		} else {
			modelAndView.addObject("message", "The link is invalid or broken!");
			modelAndView.setViewName("error");
		}

		return modelAndView;
	}
}
