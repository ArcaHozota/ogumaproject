package jp.co.ogumaproject.ppok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import jp.co.ogumaproject.ppok.commons.OgumaConstants;
import lombok.extern.log4j.Log4j2;

/**
 * Ogumaアプリケーション
 *
 * @author ArkamaHozota
 * @since 1.00beta
 */
@Log4j2
@SpringBootApplication
@ServletComponentScan
public class OgumaProjectApplication {
	public static void main(final String[] args) {
		SpringApplication.run(OgumaProjectApplication.class, args);
		log.info(OgumaConstants.MSG003);
	}
}
