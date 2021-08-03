package com.ant.linker.module.shared.mapper;

import java.util.List;

import org.json.JSONObject;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.Unit;
import com.ant.linker.data.entity.values.type.AbstractValue;
import com.ant.linker.module.shared.dto.product.CharacteristicDto;
import com.ant.linker.module.shared.dto.product.FilterValuesDto;
import com.ant.linker.module.shared.dto.product.UnitDto;
import com.ant.linker.module.shared.factory.IAbstractValueFactory;

@Mapper(componentModel = "spring")
@Component

public abstract class ICharacteristicMapper {

	@Autowired
	private IAbstractValueFactory abstractValueFactory;
	
	@IterableMapping(qualifiedByName = "mappSaved")
	public abstract List<CharacteristicDto> caractEntitiesToDtos(List<Characteristic<AbstractValue>> characteristics);

	@Named("mappSaved")
	public CharacteristicDto characteristicToDtoWithData(Characteristic<AbstractValue> characteristic) {
		CharacteristicDto characteristicDto = characteristicToDto(characteristic);
		JSONObject JsonValue = abstractValueFactory.makeJsonFromValue(characteristic.getValue(), characteristic.getValueType());
		characteristicDto.setValue(JsonValue);
		return characteristicDto;
	}
	
	@Mappings({ @Mapping(source = "label", target = "label"),
		@Mapping(source = "unit.labelUnit", target = "unit"), 
		@Mapping(ignore = true, target = "value"),
		@Mapping(source = "textFormatOnly", target = "textFormatOnly"),
		@Mapping(source = "valueType", target = "valueType")
	})
	public abstract CharacteristicDto characteristicToDto(Characteristic<AbstractValue> characteristic);
	
	@Mappings({ @Mapping(source = "label", target = "label"),
			@Mapping(ignore = true, target = "unit"), 
			@Mapping(ignore = true, target = "value"),
			@Mapping(source = "textFormatOnly", target = "textFormatOnly"),
			@Mapping(source = "valueType", target = "valueType")
	})
	public abstract Characteristic<AbstractValue> dtoToEntity(CharacteristicDto characteristicDto);

	@Mappings({ @Mapping(source = "labelUnit", target = "labelUnit"),
		@Mapping(source = "descriptionUnit", target = "descriptionUnit")
	})
	public abstract UnitDto entityToDto(Unit unit);
	
	@Mappings({ @Mapping(source = "labelUnit", target = "labelUnit"),
		@Mapping(source = "descriptionUnit", target = "descriptionUnit"), })
	public abstract Unit dtoToEntity(UnitDto unitDto);
	
	

	public abstract List<UnitDto> entitiesToDtos(List<Unit> unit);
	
	public abstract List<Unit> dtosToEntities(List<UnitDto> unitDto);

	
	
}
