package jp.co.ogumaproject.ppok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * インデクスコントローラ
 *
 * @author Administrator
 * @since 1.02
 */
@Controller
public class IndexController {

	@GetMapping("/company/")
	String comapny() {
		return "contents/company";
	}

	@GetMapping("/contact/")
	String contact() {
		return "contents/contact";
	}

	@GetMapping("/")
	String index() {
		return "index";
	}

	@GetMapping("/recruit/")
	String recruit() {
		return "contents/recruit";
	}

	@GetMapping("/service/")
	String service() {
		return "contents/service";
	}
}