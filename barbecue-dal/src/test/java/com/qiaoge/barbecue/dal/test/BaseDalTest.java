package com.qiaoge.barbecue.dal.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qiaoge.barbecue.dal.config.DataSourceConfiguration;
import com.qiaoge.barbecue.dal.config.MyBatisConfigurer;
import com.qiaoge.barbecue.dal.config.MyBatisMapperScannerConfig;
import com.qiaoge.barbecue.dal.dao.TestsDAO;
import com.qiaoge.barbecue.dal.model.Tests;
import com.qiaoge.barbecue.utils.enums.DeleteFlag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfiguration.class, MyBatisConfigurer.class,
		MyBatisMapperScannerConfig.class })
public class BaseDalTest {

	@Autowired
	private TestsDAO testsDAO;

	@Test
	public void test() {
		Tests tests = new Tests();
		tests.setCreateUserId(1);
		tests.setDeleteFlag(DeleteFlag.NOT_DELETE.getValue());
		tests.setUpdateUserId(1);
		tests.setGmtCreate(new Date());
		tests.setGmtModify(new Date());
		try {
			testsDAO.insert(tests);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

	}

}
