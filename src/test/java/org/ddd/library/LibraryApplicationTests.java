package org.ddd.library;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

import java.util.Optional;

@SpringBootTest
class LibraryApplicationTests {
	private static final Logger LOGGER = LoggerFactory.getLogger(LibraryApplicationTests.class);

	@Test
	void contextLoads() {
	}

	@Test
	void verifyModules() {
		LOGGER.info("###Modulith verification###");
		var modules = ApplicationModules.of(LibraryApplication.class);
		LOGGER.info(modules.toString());
		// avoid DDD violations among Modules:
		modules.verify();
		LOGGER.info("###Modulith verification PASSED###");
	}
}
