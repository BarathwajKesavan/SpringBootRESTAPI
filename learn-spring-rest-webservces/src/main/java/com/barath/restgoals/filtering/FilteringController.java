package com.barath.restgoals.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering-static")
	public SomeBean getSomeBean() {
		return new SomeBean("value1","value2","value3");
	}
	@GetMapping("/filtering-list-static")
	public List<SomeBean> getSomeBeans() {
		return  Arrays.asList(new SomeBean("value1","value2","value3"), new SomeBean("value4","value5","value6"));
	}
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue getSomeBeanDynamic() {
		SomeBeanDynamic bean = new SomeBeanDynamic("value1","value2","value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
		setDynamicFilters(mappingJacksonValue,"field1","field2");
		return mappingJacksonValue ;
	}
	@GetMapping("/filtering-list-dynamic")
	public MappingJacksonValue getSomeBeansDynamic() {
		List<SomeBeanDynamic> someBeanDynamicList = Arrays.asList(new SomeBeanDynamic("value1","value2","value3"), new SomeBeanDynamic("value4","value5","value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanDynamicList);
		setDynamicFilters(mappingJacksonValue,"field1","field3");
		return  mappingJacksonValue;
				
	}
	/**
	 * @param mappingJacksonValue
	 */
	private void setDynamicFilters(MappingJacksonValue mappingJacksonValue,String... propertyArray ) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(propertyArray);
		FilterProvider filters = new SimpleFilterProvider().addFilter("somefilter", filter );
		mappingJacksonValue.setFilters(filters );
	}

}
