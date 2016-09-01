package DAO.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import DAO.connection.DBconnection;
import DAO.pojo.Benchdata;
import data.BenchData.BenchData;
import data.BenchData.BenchRecord;
import dataservice.BenchDataService.BenchRecordService;
import dataservice.connection.connection;

public class BenchDataTest {
	BenchData benchData=new BenchData();
	DBconnection DBconnection;
	
	@Before
	public void init(){
		benchData=new BenchData();
		DBconnection=new DBconnection();
	}
	
	@Test
	public void testGetBench() {
		long now=0;
		long next=0;
		try {
			System.out.println(System.currentTimeMillis());
			System.out.println(benchData.getBench().size());
			System.out.println(System.currentTimeMillis());
			Thread.sleep(2000);
			System.out.println(System.currentTimeMillis());
			System.out.println(benchData.getBench().size());
			System.out.println(System.currentTimeMillis());
			System.out.println(System.currentTimeMillis());
			System.out.println(benchData.getBench().size());
			System.out.println(System.currentTimeMillis());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BenchRecordService br=new BenchRecord();
		System.out.println(System.currentTimeMillis());
		try {
			System.out.println(br.getBenchData("sh000001", "2013-01-01", "2016-09-02").size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis());
		try {
			System.out.println(System.currentTimeMillis());
			System.out.println(benchData.getBench().size());
			System.out.println(System.currentTimeMillis());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
