/**
 * 
 */
package com.multigold.mdm.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huanggaoren
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public abstract class AbstractServiceTest {
	private static final Log log = LogFactory.getLog(AbstractServiceTest.class);

	public AbstractServiceTest() {
	}

	public void printInfo(Object obj) {
		log.info(obj);
	}

	public void printDebug(Object obj) {
		log.debug(obj);
	}

	public void printError(Object obj) {
		log.error(obj);
	}

	public void printTrace(Object obj) {
		log.trace(obj);
	}
}
