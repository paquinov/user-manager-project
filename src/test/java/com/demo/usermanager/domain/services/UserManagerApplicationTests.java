package com.demo.usermanager.domain.services;

import com.demo.usermanager.domain.data.Person;
import com.demo.usermanager.domain.data.Phone;
import com.demo.usermanager.domain.data.User;
import com.demo.usermanager.domain.exceptions.AlreadyRegisteredException;
import com.demo.usermanager.domain.exceptions.RegisterUserInputValidationsException;
import com.demo.usermanager.domain.jwt.JwtUtils;
import com.demo.usermanager.domain.ports.spi.UserPersistencePort;
import com.demo.usermanager.domain.validators.RegisterUserInputValidator;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("User Manager Tests")
class UserManagerApplicationTests {

	private UserPersistencePort userPersistencePort;
	private RegisterUserInputValidator inputValidator;
	private PasswordEncoder passwordEncoder;
	private JwtUtils jwtUtils;
	private UserManagerImpl userManager;

	@BeforeEach
	void setup() {
		userPersistencePort = Mockito.mock(UserPersistencePort.class);
		inputValidator = Mockito.mock(RegisterUserInputValidator.class);
		passwordEncoder = Mockito.mock(PasswordEncoder.class);
		jwtUtils = Mockito.mock(JwtUtils.class);
		userManager = new UserManagerImpl(userPersistencePort, inputValidator, passwordEncoder, jwtUtils);
	}

	@Test
	@DisplayName("If all validations pass ok and no malfunction is presented then this test passes")
	void whenEverythingIsCorrectThenNoExceptionIsThrown() {
		User user = buildInitialUser();
		Mockito.when(inputValidator.validate(user)).thenReturn(Collections.emptyList());
		Mockito.when(userPersistencePort.emailIsAlreadyRegistered(user.getEmail())).thenReturn(false);
		Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn("4IwA2Tzt");
		Mockito.when(jwtUtils.generateToken(Mockito.any(), Mockito.any())).thenReturn("27bmq2c");
		Assertions.assertDoesNotThrow(() -> userManager.registerUser(user), "Some error has happened");
	}

	@Test
	@DisplayName("If the email is already registered then an exception will be thrown")
	void whenEmailIsAlreadyRegisteredThenExceptionIsThrown() {
		User user = buildInitialUser();
		Mockito.when(inputValidator.validate(user)).thenReturn(Collections.emptyList());
		Mockito.when(userPersistencePort.emailIsAlreadyRegistered(user.getEmail())).thenReturn(true);
		Assertions.assertThrows(AlreadyRegisteredException.class, () -> userManager.registerUser(user));
	}

	@Test
	@DisplayName("If some input value is not in a correct format then an exception will be thrown")
	void whenSomeInputValueHasAnIncorrectFormatThenExceptionIsThrown() {
		User user = buildInitialUser();
		Mockito.when(inputValidator.validate(user)).thenReturn(Collections.singletonList("La contrasenia no cumple el formato establecido"));
		Assertions.assertThrows(RegisterUserInputValidationsException.class, () -> userManager.registerUser(user));
	}

	private User buildInitialUser() {
		return User.builder()
					.email("juan@rodriguez.org")
					.password("123456789")
					.person(Person.builder()
								.name("Juan Rodriguez")
								.phoneList(List.of(Phone.builder()
														.number("1234567")
														.cityCode("01")
														.countryCode("51")
														.build(),
													Phone.builder()
														.number("8765432")
														.cityCode("02")
														.countryCode("52")
														.build()))
								.build())
					.build();
	}

}
