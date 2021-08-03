package com.ant.linker.module.caracteristic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.linker.data.dao.IAbstractCategoryDao;
import com.ant.linker.data.dao.ICharacteristicDao;
import com.ant.linker.data.dao.IProductDao;
import com.ant.linker.data.dao.IUnitDao;
import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.Unit;
import com.ant.linker.data.entity.values.type.AbstractValue;
import com.ant.linker.data.entity.values.type.CaractValueType;
import com.ant.linker.data.entity.values.type.ListValue;
import com.ant.linker.data.entity.values.type.MinMaxValue;
import com.ant.linker.data.entity.values.type.SimpleValue;
import com.ant.linker.module.product.service.IProductService;
import com.ant.linker.module.shared.dto.product.CharacteristicDto;
import com.ant.linker.module.shared.dto.product.DropDownReceivedCatDto;
import com.ant.linker.module.shared.dto.product.DropDownReceivedDto;
import com.ant.linker.module.shared.dto.product.FilterDropDownDto;
import com.ant.linker.module.shared.dto.product.FilterRangeDto;
import com.ant.linker.module.shared.dto.product.FilterValuesDto;
import com.ant.linker.module.shared.dto.product.MinMaxDto;
import com.ant.linker.module.shared.dto.product.ProductFiltersDto;
import com.ant.linker.module.shared.dto.product.RangeReceivedCatDto;
import com.ant.linker.module.shared.dto.product.RangeReceivedDto;
import com.ant.linker.module.shared.dto.product.SearchResultDto;
import com.ant.linker.module.shared.dto.product.UnitDto;
import com.ant.linker.module.shared.factory.IAbstractValueFactory;
import com.ant.linker.module.shared.mapper.ICharacteristicMapper;

@Service
public class CaracteristicServiceImp implements ICaracteristicService {

	private static final String String = null;
	@Autowired
	private ICharacteristicDao characteristicDao;
	@Autowired
	private IUnitDao unitDao;

	@Autowired
	private ICharacteristicMapper iCharacteristicMapper;

	@Autowired
	private IAbstractValueFactory abstractValueFactory;

	@Autowired
	private IProductService productservice;
	@Autowired
	private IAbstractCategoryDao abstractCategoryDao;
	@Autowired
	private IProductDao productdao;

	@Autowired
	private IProductService productService;

	@Override
	public List<Characteristic<AbstractValue>> createCaracteristic(List<CharacteristicDto> characteristicDtos,
			Product product) {
		List<Characteristic<AbstractValue>> caracteristics = new ArrayList<>();
		characteristicDtos.forEach(caracteristic -> {

			AbstractValue value = abstractValueFactory.makeValueFromJson(new JSONObject(caracteristic.getValue()),
					caracteristic.getValueType());
			characteristicDao.saveAbstractValue(value);

			Characteristic<AbstractValue> caracteristicEntity = iCharacteristicMapper.dtoToEntity(caracteristic);
			caracteristicEntity.setValue(value);

			if (!caracteristic.isTextFormatOnly()) {
				Unit unit = getUnitByLabel(caracteristic.getUnit());
				if (unit != null) {
					caracteristicEntity.setUnit(unit);
				}
			}

			caracteristicEntity.addProduct(product);
			characteristicDao.save(caracteristicEntity);
			caracteristics.add(caracteristicEntity);
		});

		return caracteristics;
	}

	@Override
	public UnitDto createUnit(UnitDto unitDto) {
		String labelUnit = unitDto.getLabelUnit();
		Unit unit = unitDao.loadUnitByLabel(labelUnit);
		if (unit == null) {
			unitDto.setUnitExist(false);
			unit = iCharacteristicMapper.dtoToEntity(unitDto);
			unitDao.save(unit);
		} else {
			unitDto.setUnitExist(true);

		}
		return unitDto;
	}

	@Override
	public Unit getUnitByLabel(String labelUnit) {
		return unitDao.loadUnitByLabel(labelUnit);
	}

	@Override
	public List<UnitDto> loadListUnit() {
		List<Unit> listUnit = unitDao.loadListUnit();
		return iCharacteristicMapper.entitiesToDtos(listUnit);
	}

	@Override
	public ProductFiltersDto getFilters(List<Product> list) {

		Map<String, FilterRangeDto> allRangeFilters = new HashMap<>();
		Map<String, FilterDropDownDto> allDropDownFilters = new HashMap<>();

		for (Product product : list) {
			for (Characteristic<AbstractValue> c : product.getListeCharacteristic()) {
				try {
					if (!c.isTextFormatOnly()) { // BLOC DE TRAITEMENT RANGE ///////////////////////////////////////
						java.lang.String label = c.getLabel();
						FilterRangeDto FilterRangeDto = allRangeFilters.get(label);

						if (FilterRangeDto == null) { // LABEL NON PRESENT DANS LA LISTE DE FILTRES
							switch (c.getValueType()) {
							case MIN_MAX_VALUE: {
								String minValue = ((MinMaxValue) c.getValue()).getMinValue();
								String maxValue = ((MinMaxValue) c.getValue()).getMaxValue();
								String unit = c.getUnit().getLabelUnit();
								MinMaxDto minMax = new MinMaxDto(unit, minValue, maxValue);
								List<MinMaxDto> ListMinMax = new ArrayList<MinMaxDto>();
								ListMinMax.add(minMax);
								FilterRangeDto filterRange = new FilterRangeDto(c.getLabel(), ListMinMax);
								allRangeFilters.put(c.getLabel(), filterRange);
								break;
							}
							case LIST_VALUE: {
								String minValue = null;
								String maxValue = null;
								String unit = c.getUnit().getLabelUnit();
								List<String> listValue = ((ListValue) c.getValue()).getValues();
								for (String valueOfList : listValue) {
									if (minValue == null && maxValue == null) {
										maxValue = minValue = valueOfList;

									} else {
										if (Double.parseDouble(minValue) > Double.parseDouble(valueOfList)) {
											minValue = valueOfList;
										} else {
											if (Double.parseDouble(maxValue) < Double.parseDouble(valueOfList)) {
												maxValue = valueOfList;
											}
										}
									}
								}
								MinMaxDto minMax = new MinMaxDto(unit, minValue, maxValue);
								List<MinMaxDto> ListMinMax = new ArrayList<MinMaxDto>();
								ListMinMax.add(minMax);
								FilterRangeDto filterRange = new FilterRangeDto(c.getLabel(), ListMinMax);
								allRangeFilters.put(c.getLabel(), filterRange);
								break;
							}
							case SIMPLE_VALUE: {
								String minValue = ((SimpleValue) c.getValue()).getValue();
								String maxValue = ((SimpleValue) c.getValue()).getValue();
								String unit = c.getUnit().getLabelUnit();
								MinMaxDto minMax = new MinMaxDto(unit, minValue, maxValue);
								List<MinMaxDto> ListMinMax = new ArrayList<MinMaxDto>();
								ListMinMax.add(minMax);
								FilterRangeDto filterRange = new FilterRangeDto(c.getLabel(), ListMinMax);
								allRangeFilters.put(c.getLabel(), filterRange);
								break;

							}
							default: {

							}
							}
						}

						else { // LABEL PRESENT DANS LA LISTE DE FILTRES
							List<String> units = new ArrayList<String>();
							for (MinMaxDto minmax : FilterRangeDto.getValue()) {
								units.add(minmax.getUnit());
							}
							if (units.contains(c.getUnit().getLabelUnit())) { // UNITE PRESENTE DANS LE FILTRE
								for (MinMaxDto minmax : FilterRangeDto.getValue()) {
									if (minmax.getUnit().equals(c.getUnit().getLabelUnit())) {
										switch (c.getValueType()) {
										case MIN_MAX_VALUE: {
											if (Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) < Double
													.parseDouble(minmax.getMin())) {
												minmax.setMin((((MinMaxValue) c.getValue()).getMinValue()));

											}
											if (Double.parseDouble(((MinMaxValue) c.getValue()).getMaxValue()) > Double
													.parseDouble(minmax.getMax())) {
												minmax.setMax((((MinMaxValue) c.getValue()).getMaxValue()));
											}
											break;

										}
										case LIST_VALUE: {
											String minValue = null;
											String maxValue = null;

											List<String> listValue = ((ListValue) c.getValue()).getValues();
											for (String valueOfList : listValue) {
												if (minValue == null && maxValue == null) {
													maxValue = minValue = valueOfList;

												} else {
													if (Double.parseDouble(minValue) > Double
															.parseDouble(valueOfList)) {
														minValue = valueOfList;
													} else {
														if (Double.parseDouble(maxValue) < Double
																.parseDouble(valueOfList)) {
															maxValue = valueOfList;
														}
													}
												}
											}
											if (Double.parseDouble(minValue) < Double.parseDouble(minmax.getMin())) {
												minmax.setMin(minValue);
												break;
											}
											if (Double.parseDouble(maxValue) > Double.parseDouble(minmax.getMax())) {
												minmax.setMax(maxValue);
											}
											break;

										}
										case SIMPLE_VALUE: {
											if (Double.parseDouble(((SimpleValue) c.getValue()).getValue()) < Double
													.parseDouble(minmax.getMin())) {
												minmax.setMin((((SimpleValue) c.getValue()).getValue()));
												break;
											}
											if (Double.parseDouble(((SimpleValue) c.getValue()).getValue()) > Double
													.parseDouble(minmax.getMax())) {
												minmax.setMax((((SimpleValue) c.getValue()).getValue()));
											}
											break;

										}
										default: {

										}
										}
									}
								}

							} else { // UNITE NON PRESENTE DANS LE FILTRE
								switch (c.getValueType()) {
								case MIN_MAX_VALUE: {
									String minValue = ((MinMaxValue) c.getValue()).getMinValue();
									String maxValue = ((MinMaxValue) c.getValue()).getMaxValue();
									String unit = c.getUnit().getLabelUnit();
									MinMaxDto newMinMax = new MinMaxDto(unit, minValue, maxValue);
									FilterRangeDto.getValue().add(newMinMax);
									break;

								}
								case LIST_VALUE: {
									String minValue = null;
									String maxValue = null;
									String unit = c.getUnit().getLabelUnit();
									List<String> listValue = ((ListValue) c.getValue()).getValues();
									for (String valueOfList : listValue) {
										if (minValue == null && maxValue == null) {
											maxValue = minValue = valueOfList;

										} else {
											if (Double.parseDouble(minValue) > Double.parseDouble(valueOfList)) {
												minValue = valueOfList;
											} else {
												if (Double.parseDouble(maxValue) < Double.parseDouble(valueOfList)) {
													maxValue = valueOfList;

												}
											}
										}
									}
									MinMaxDto newMinMax = new MinMaxDto(unit, minValue, maxValue);
									FilterRangeDto.getValue().add(newMinMax);
									break;

								}
								case SIMPLE_VALUE: {
									String minValue = ((SimpleValue) c.getValue()).getValue();
									String maxValue = ((SimpleValue) c.getValue()).getValue();
									String unit = c.getUnit().getLabelUnit();
									MinMaxDto newMinMax = new MinMaxDto(unit, minValue, maxValue);
									FilterRangeDto.getValue().add(newMinMax);
									break;

								}
								default: {

								}
								}

							}
						}

					} else { // BLOC DE TRAITEMENT TEXTONLY
								// /////////////////////////////////////////////////////////////

						java.lang.String label = c.getLabel();
						FilterDropDownDto FilterDropDownDto = allDropDownFilters.get(label);
						if (FilterDropDownDto == null) { // LABEL NON PRESENT DANS LA LISTE DE FILTRES
							switch (c.getValueType()) {

							case LIST_VALUE: {

								List<String> listValue = ((ListValue) c.getValue()).getValues();
								FilterDropDownDto filterDropDown = new FilterDropDownDto(c.getLabel(), listValue);
								allDropDownFilters.put(c.getLabel(), filterDropDown);
								break;
							}
							case SIMPLE_VALUE: {
								List<String> listValue = new ArrayList<String>();
								listValue.add(((SimpleValue) c.getValue()).getValue());
								FilterDropDownDto filterDropDown = new FilterDropDownDto(c.getLabel(), listValue);
								allDropDownFilters.put(c.getLabel(), filterDropDown);
								break;

							}
							default: {

							}
							}

						} else { // LABEL PRESENT DANS LA LISTE DE FILTRES
							switch (c.getValueType()) {

							case LIST_VALUE: {

								List<String> listValue = ((ListValue) c.getValue()).getValues();
								for (String valueOfList : listValue) {
									if (!FilterDropDownDto.getTextOnlyList().contains(valueOfList)) {
										FilterDropDownDto.addValue(valueOfList);
									}
								}
								break;

							}
							case SIMPLE_VALUE: {
								if (!FilterDropDownDto.getTextOnlyList()
										.contains(((SimpleValue) c.getValue()).getValue())) {
									FilterDropDownDto.addValue(((SimpleValue) c.getValue()).getValue());
								}
								break;

							}
							default: {

							}
							}

						}
					} //////////////////////////////////////////////////

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
		List<FilterRangeDto> allRange = new ArrayList<FilterRangeDto>(allRangeFilters.values());
		List<FilterDropDownDto> allDropDown = new ArrayList<FilterDropDownDto>(allDropDownFilters.values());
		ProductFiltersDto allFilters = new ProductFiltersDto(allRange, allDropDown);
		return allFilters;

	}

	@Override
	public SearchResultDto applyRangeFilter(RangeReceivedDto f) {

		List<Product> listeProduct = productdao.loadAllProducts();
		List<Product> listeResult = new ArrayList<>();

		for (Product product : listeProduct) {
			for (Characteristic<AbstractValue> c : product.getListeCharacteristic()) {
				if (c.getLabel().equals(f.getLabel())) {
					if (!c.isTextFormatOnly()) {
						if (c.getUnit().getLabelUnit().equals(f.getUnit())) {

							switch (c.getValueType()) {
							case SIMPLE_VALUE: {
								if (Double.parseDouble(((SimpleValue) c.getValue()).getValue()) >= Double
										.parseDouble(f.getMin())
										&& Double.parseDouble(((SimpleValue) c.getValue()).getValue()) <= Double
												.parseDouble(f.getMax())) {
									listeResult.add(product);
								}
								break;

							}
							case MIN_MAX_VALUE: {

								if ((Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) > Double
										.parseDouble(f.getMin())
										&& Double.parseDouble(((MinMaxValue) c.getValue()).getMaxValue()) < Double
												.parseDouble(f.getMax()))
										|| (Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) < Double
												.parseDouble(f.getMin())
												&& Double.parseDouble(
														((MinMaxValue) c.getValue()).getMaxValue()) > Double
																.parseDouble(f.getMax()))
										|| (Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) <= Double
												.parseDouble(f.getMin())
												&& Double.parseDouble(
														((MinMaxValue) c.getValue()).getMaxValue()) >= Double
																.parseDouble(f.getMin()))
										|| (Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) <= Double
												.parseDouble(f.getMax())
												&& Double.parseDouble(
														((MinMaxValue) c.getValue()).getMaxValue()) >= Double
																.parseDouble(f.getMax()))) {
									listeResult.add(product);
								}

								break;

							}
							case LIST_VALUE: {
								List<String> listValue = ((ListValue) c.getValue()).getValues();
								for (String valueOfList : listValue) {
									if (Double.parseDouble(valueOfList) >= Double.parseDouble(f.getMin())
											&& Double.parseDouble(valueOfList) <= Double.parseDouble(f.getMax())) {
										listeResult.add(product);
										break;
									}

								}

								break;

							}
							default: {

							}
							}

						}

					}

				}

			}
		}

		return productService.searchProductAfterFilter(f.keyword, listeResult);

	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public SearchResultDto applyDropDownFilter(DropDownReceivedDto f) {

		List<Product> listeProduct = productdao.loadAllProducts();
		List<Product> listeResult = new ArrayList<>();

		for (Product product : listeProduct) {
			for (Characteristic<AbstractValue> c : product.getListeCharacteristic()) {
				if (c.isTextFormatOnly()) {
					if (c.getLabel().equals(f.label)) {
						switch (c.getValueType()) {

						case LIST_VALUE: {
							List<String> listValue = ((ListValue) c.getValue()).getValues();
							if (listValue.contains(f.value)) {
								listeResult.add(product);
								break;
							}
							break;
						}
						case SIMPLE_VALUE: {

							if (((SimpleValue) c.getValue()).getValue().equals(f.value)) {
								listeResult.add(product);
								break;
							}
							break;
						}
						default: {

						}
						}
					}

				}
			}
		}

		return productService.searchProductAfterFilter(f.keyword, listeResult);
	}

	@Override
	public SearchResultDto applyRangeFilterCat(RangeReceivedCatDto f) {

		FinalCategory finalCategory = abstractCategoryDao.loadFinalCategoryByref(f.refCat);
		List<Product> listeProduct = finalCategory.getListeProduct();
		List<Product> listeResult = new ArrayList<>();

		for (Product product : listeProduct) {
			for (Characteristic<AbstractValue> c : product.getListeCharacteristic()) {
				if (c.getLabel().equals(f.getLabel())) {
					if (!c.isTextFormatOnly()) {
						if (c.getUnit().getLabelUnit().equals(f.getUnit())) {

							switch (c.getValueType()) {
							case SIMPLE_VALUE: {

								if (Double.parseDouble(((SimpleValue) c.getValue()).getValue()) >= Double
										.parseDouble(f.getMin())
										&& Double.parseDouble(((SimpleValue) c.getValue()).getValue()) <= Double
												.parseDouble(f.getMax())) {
									listeResult.add(product);
								}
								break;

							}
							case MIN_MAX_VALUE: {
								if ((Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) > Double
										.parseDouble(f.getMin())
										&& Double.parseDouble(((MinMaxValue) c.getValue()).getMaxValue()) < Double
												.parseDouble(f.getMax()))
										|| (Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) < Double
												.parseDouble(f.getMin())
												&& Double.parseDouble(
														((MinMaxValue) c.getValue()).getMaxValue()) > Double
																.parseDouble(f.getMax()))
										|| (Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) <= Double
												.parseDouble(f.getMin())
												&& Double.parseDouble(
														((MinMaxValue) c.getValue()).getMaxValue()) >= Double
																.parseDouble(f.getMin()))
										|| (Double.parseDouble(((MinMaxValue) c.getValue()).getMinValue()) <= Double
												.parseDouble(f.getMax())
												&& Double.parseDouble(
														((MinMaxValue) c.getValue()).getMaxValue()) >= Double
																.parseDouble(f.getMax())))

								{
									listeResult.add(product);
								}

								break;

							}
							case LIST_VALUE: {

								List<String> listValue = ((ListValue) c.getValue()).getValues();
								for (String valueOfList : listValue) {
									if (Double.parseDouble(valueOfList) >= Double.parseDouble(f.getMin())
											&& Double.parseDouble(valueOfList) <= Double.parseDouble(f.getMax())) {
										listeResult.add(product);
										break;
									}

								}

								break;

							}
							default: {

							}
							}

						}

					}

				}

			}
		}

		return productService.searchProductAfterFilterCat(listeResult);

	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public SearchResultDto applyDropDownFilterCat(DropDownReceivedCatDto f) {

		FinalCategory finalCategory = abstractCategoryDao.loadFinalCategoryByref(f.refCat);
		List<Product> listeProduct = finalCategory.getListeProduct();
		List<Product> listeResult = new ArrayList<>();

		for (Product product : listeProduct) {
			for (Characteristic<AbstractValue> c : product.getListeCharacteristic()) {
				if (c.isTextFormatOnly()) {
					if (c.getLabel().equals(f.label)) {
						switch (c.getValueType()) {

						case LIST_VALUE: {
							List<String> listValue = ((ListValue) c.getValue()).getValues();
							if (listValue.contains(f.value)) {
								listeResult.add(product);
								break;
							}
							break;
						}
						case SIMPLE_VALUE: {

							if (((SimpleValue) c.getValue()).getValue().equals(f.value)) {
								listeResult.add(product);
								break;
							}
							break;
						}
						default: {

						}
						}
					}

				}
			}
		}

		return productService.searchProductAfterFilterCat(listeResult);
	}

}
