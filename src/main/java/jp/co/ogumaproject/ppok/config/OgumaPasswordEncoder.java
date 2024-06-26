package jp.co.ogumaproject.ppok.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.NoArgsConstructor;

/**
 * パスワードエンコーダ
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@NoArgsConstructor
public final class OgumaPasswordEncoder implements PasswordEncoder {

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(BCryptVersion.$2Y, 7);

	@Override
	public String encode(final CharSequence rawPassword) {
		return this.passwordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(final CharSequence rawPassword, final String encodedPassword) {
		return this.passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
