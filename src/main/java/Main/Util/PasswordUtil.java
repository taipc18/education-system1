package Main.Util;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static String generatePassword(int length) {
		String numbers = "1234567890";
		Random random = new Random();
		StringBuilder password = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			password.append(numbers.charAt(random.nextInt(numbers.length())));
		}
		return password.toString();
	}
}
