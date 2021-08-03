package com.ant.linker.module.caracteristic.service;

import java.util.List;
import java.util.Map;

import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.Unit;
import com.ant.linker.data.entity.values.type.AbstractValue;
import com.ant.linker.module.shared.dto.product.CharacteristicDto;
import com.ant.linker.module.shared.dto.product.DropDownReceivedCatDto;
import com.ant.linker.module.shared.dto.product.DropDownReceivedDto;
import com.ant.linker.module.shared.dto.product.FilterDropDownDto;
import com.ant.linker.module.shared.dto.product.FilterRangeDto;
import com.ant.linker.module.shared.dto.product.ProductFiltersDto;
import com.ant.linker.module.shared.dto.product.RangeReceivedCatDto;
import com.ant.linker.module.shared.dto.product.RangeReceivedDto;
import com.ant.linker.module.shared.dto.product.SearchResultDto;
import com.ant.linker.module.shared.dto.product.UnitDto;

public interface ICaracteristicService {

	UnitDto createUnit(UnitDto unitDto);
	Unit getUnitByLabel(String labelUnit);
	public List<Characteristic<AbstractValue>> createCaracteristic(List<CharacteristicDto> characteristicDtos, Product product) ;
	public List<UnitDto> loadListUnit();
	
	public ProductFiltersDto getFilters(List<Product> list);
	public SearchResultDto applyRangeFilter (RangeReceivedDto f) ;
	public SearchResultDto applyDropDownFilter (DropDownReceivedDto f) ;
	public SearchResultDto applyRangeFilterCat (RangeReceivedCatDto f) ;
	public SearchResultDto applyDropDownFilterCat (DropDownReceivedCatDto f) ;
}
