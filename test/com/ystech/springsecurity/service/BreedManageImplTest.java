/**
 * 
 */
package com.ystech.springsecurity.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.service.BreedManageImpl;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.test.SpringTxTestCase;
import com.ystech.core.util.TestGenerateUtil;

/**
 * @author shusanzhan
 * @date 2013-6-22
 */
public class BreedManageImplTest extends SpringTxTestCase{
	private BreedManageImpl breedManageImpl;

	@Resource
	public void setBreedManageImpl(BreedManageImpl breedManageImpl) {
		this.breedManageImpl = breedManageImpl;
	}
	@Test
	public void testCRUD() throws Exception {
		//create
		Breed breed=new Breed();

		breed.setName("name");
		breed.setCharacteristic("characteristic");
		breed.setNote("note");
		breed.setCharCode("charCode");

		breedManageImpl.save(breed);
		assertNotNull(breed.getDbid());
		//get
		breed=breedManageImpl.get(breed.getDbid());
		assertNotNull(breed.getDbid());
		assertEquals("name",breed.getName());
		assertEquals("characteristic",breed.getCharacteristic());
		assertEquals("note",breed.getNote());
		assertEquals("charCode",breed.getCharCode());

		//update
		breed.setName("name1");
		breedManageImpl.save(breed);
		breed=breedManageImpl.get(breed.getDbid());
		assertNotNull(breed.getDbid());
		assertEquals("name1",breed.getName());
		assertEquals("characteristic",breed.getCharacteristic());
		assertEquals("note",breed.getNote());
		assertEquals("charCode",breed.getCharCode());

		//delete
		breedManageImpl.deleteById(breed.getDbid());

	}
	public static void main(String[] args) {
		TestGenerateUtil.generate(Breed.class);
	}
	
}
