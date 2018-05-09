package com.myweb;

import com.myweb.domain.District;
import com.myweb.domain.Pager;
import com.myweb.mapper.DistrictMapper;
import com.myweb.service.DistrictService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictTests {

	@Autowired
	private DistrictService districtService;

	@Test
	public void getTotal(){
		/*Optional<Pager<District>> optionalPager =
				districtService.find("湖南", 1, 10);
        System.out.println(optionalPager.isPresent());*/
        List<District> districts = districtService.findByParam("湖南");
        for(District district : districts){
            System.out.println(district.getCity().getAreacode());
        }

	}

	@Test
	public void find(){

	}
}
