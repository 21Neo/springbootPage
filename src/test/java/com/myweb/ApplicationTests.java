package com.myweb;

import com.myweb.domain.District;
import com.myweb.domain.Movie;
import com.myweb.mapper.DistrictMapper;
import com.myweb.service.DistrictService;
import com.myweb.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private DistrictMapper districtMapper;

	@Test
	public void getTotal(){
		Long total = districtMapper.getTotal("湖南");
		System.out.println(total);
	}

	@Test
	public void find(){
		List<District> districts = districtMapper.find(null,1,10);
		districts.forEach(System.out::println);
	}
}
