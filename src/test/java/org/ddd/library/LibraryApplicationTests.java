package org.ddd.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class LibraryApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void verifyModules() {
		var modules = ApplicationModules.of(LibraryApplication.class);
		System.out.println(modules);
		// avoid DDD violations among Modules:
		modules.verify();
		System.out.println("###Modulith verification PASSED###");
	}
}
