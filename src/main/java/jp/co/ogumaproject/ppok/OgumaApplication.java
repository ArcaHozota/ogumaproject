package jp.co.ogumaproject.ppok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import jp.co.ogumaproject.ppok.commons.OgumaProjectConstants;
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
public class OgumaApplication {
	public static void main(final String[] args) {
		SpringApplication.run(OgumaApplication.class, args);
		log.info(OgumaProjectConstants.MESSAGE_SPRING_APPLICATION);
	}
}
