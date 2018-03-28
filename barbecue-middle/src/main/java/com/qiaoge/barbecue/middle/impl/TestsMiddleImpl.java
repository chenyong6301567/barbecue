package com.qiaoge.barbecue.middle.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cy.util.CollectionUtils;
import com.qiaoge.barbecue.biz.manager.TestsManager;
import com.qiaoge.barbecue.dal.dao.TestsDAO;
import com.qiaoge.barbecue.dal.model.Tests;
import com.qiaoge.barbecue.dal.model.TestsExample;
import com.qiaoge.barbecue.middle.TestsMiddle;
import com.qiaoge.barbecue.utils.enums.DeleteFlag;

/**
 * @author cy
 * @Description 
 * @date 2018年3月28日上午9:47:33 
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TestsMiddleImpl implements TestsMiddle {

	@Autowired
	private TestsDAO testsDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(TestsMiddleImpl.class);

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate(Integer id) {
		Tests tests = getTestsById(id);
		if (tests != null) {
			tests.setGmtModify(new Date());
		}
		try {
			testsDAO.updateByPrimaryKey(tests);
		} catch (DataAccessException e) {
			LOGGER.error("更新异常异常", e);
			throw new RuntimeException("内部服务器异常");
		}

	}

	/**
	* @Title getTestsById
	* @author cy
	* @Description 
	* @date 2018年3月28日上午9:51:07
	* @return Tests
	* @throws:
	*/
	private Tests getTestsById(Integer id) {
		TestsExample testsExample = new TestsExample();
		TestsExample.Criteria criteria = testsExample.createCriteria();
		criteria.andDeleteFlagEqualTo(DeleteFlag.NOT_DELETE.getValue());
		criteria.andIdEqualTo(id);
		try {
			List<Tests> list = testsDAO.selectByExample(testsExample);
			return CollectionUtils.isNotEmpty(list) ? list.get(0) : null;
		} catch (DataAccessException e) {
			LOGGER.error("更新异常异常", e);
			throw new RuntimeException("内部服务器异常");
		}
	}

}
