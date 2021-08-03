package com.ant.linker.module.product.importing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.ant.linker.data.dao.IFileDao;
import com.ant.linker.data.dao.IImportConfigDao;
import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.File;
import com.ant.linker.data.entity.ImportConfig;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.Unit;
import com.ant.linker.data.entity.general.ErrorCause;
import com.ant.linker.data.entity.general.GeneralStatus;
import com.ant.linker.data.entity.general.ImportType;
import com.ant.linker.data.entity.values.type.CaractValueType;
import com.ant.linker.data.entity.values.type.ListValue;
import com.ant.linker.data.entity.values.type.MinMaxValue;
import com.ant.linker.data.entity.values.type.SimpleValue;
import com.ant.linker.module.caracteristic.service.ICaracteristicService;
import com.ant.linker.module.product.service.IProductService;
import com.ant.linker.module.shared.dto.product.CharacteristicDto;
import com.ant.linker.module.shared.dto.product.ProductCreateDto;
import com.ant.linker.module.shared.dto.product.UnitDto;
import com.ant.linker.module.shared.factory.IAbstractValueFactory;
import com.ant.linker.module.shared.factory.IFileFactory;
import com.ant.linker.module.shared.mapper.IProductCreateMapper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

@Service
public class ProductImporterImpl implements IProductImporter {
	@Autowired
	private ICSVReaderHelper csvReaderHelper;
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductCreateMapper productMapper;
	@Autowired
	private ICaracteristicService caracteristicService;

	@Autowired
	private IFileDao fileDao;

	@Autowired
	private IAbstractValueFactory valueFactory;
	@Autowired
	private IFileFactory fileFactory;

	@Autowired
	private IImportConfigDao configDao;

	private Map<String, ProductCreateDto> products;
	private Map<String, UnitDto> units;

	private boolean processForCron = false;

	public ProductImporterImpl() {
		super();
		products = new HashMap<>();
		units = new HashMap<>();
	}

	@Override
	public void execute() {

		if (processForCron) {
			processProductsInCronMode();
		} else {
			processProductsInNormalMode();
		}

	}

	private void processProductsInNormalMode() {
		processImport(ImportConstants.PRODUCT_KEY, ImportConstants.CARACTERISTIC_KEY,
				ImportConstants.PRODUCTS_FILE_PATH, ImportConstants.CARACTERISTIC_FILE_PATH);
	}

	private void processProductsInCronMode() {
		if(products != null) products.clear();
		
		ImportConfig firstConfig = configDao.loadFirstByTypeAndStatus(ImportType.PRODUCT, GeneralStatus.TO_PROCESS);
		if (firstConfig != null) {

			List<ImportConfig> indexConfigs = configDao.loadByIndexAndStatus(firstConfig.getIndex(),
					GeneralStatus.TO_PROCESS);

			Optional<ImportConfig> productConfigOpt = indexConfigs.stream()
					.filter(config -> config.getImportType() == ImportType.PRODUCT).findFirst();
			Optional<ImportConfig> caractConfigOpt = indexConfigs.stream()
					.filter(config -> config.getImportType() == ImportType.CARACTERISTIC).findFirst();
			
			

			if (productConfigOpt.isPresent() && caractConfigOpt.isPresent()) {
				ImportConfig productConfig = productConfigOpt.get();
				ImportConfig caractConfig = caractConfigOpt.get();
				
				// Set Status to In Progress
				productConfig.setStatus(GeneralStatus.IN_PROGRESS);
				configDao.save(productConfig);
				caractConfig.setStatus(GeneralStatus.IN_PROGRESS);
				configDao.save(caractConfig);
				
				String productKey = ImportConstants.PRODUCT_KEY + productConfig.getImportConfigId();
				String caractKey = ImportConstants.CARACTERISTIC_KEY + caractConfig.getImportConfigId();
				String productPath = ImportConstants.IMPORT_FOLDER + "/" + productConfig.getFilePath();
				String caractPath = ImportConstants.IMPORT_FOLDER + "/" + caractConfig.getFilePath();

				ImportResponse processImportResponse = processImport(productKey, caractKey, productPath, caractPath);

				GeneralStatus status = processImportResponse.getStatus();
				if (status == GeneralStatus.ERROR || status == GeneralStatus.WITH_ERRORS) {
					List<ErrorCause> causes = processImportResponse.getCauses();

					causes.forEach(cause -> {
						if (cause == ErrorCause.IMPORT_CARACTERISTIC) {
							caractConfig.setStatus(status);
						} else if (cause == ErrorCause.IMPORT_PRODUCT) {
							productConfig.setStatus(status);
						}
					});

				} else {
					caractConfig.setStatus(status);
					productConfig.setStatus(status);
				}

				configDao.save(productConfig);
				configDao.save(caractConfig);
			}

		}

	}

	private ImportResponse processImport(String productKey, String caractKey, String productPath, String caractPath) {

		try {
			this.csvReaderHelper.addFileToLoad(productKey, productPath);
			this.csvReaderHelper.addFileToLoad(caractKey, caractPath);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String[] nextRecord;

		while ((nextRecord = this.csvReaderHelper.getNextRecordOf(productKey)) != null) {
			try {
				String id = nextRecord[0];

				String model = nextRecord[1].toUpperCase();
				String label = nextRecord[2].toUpperCase();
				String description = nextRecord[3];
				String refCategory = nextRecord[4];
				String brand = nextRecord[5].toUpperCase();
				String imageId = nextRecord[6];
				String status = nextRecord[7];
				
				if(model.toUpperCase().compareTo(brand.toUpperCase()) == 0) {
					UUID uuid = UUID.randomUUID();
					int length = StringUtils.length(uuid.toString());
					model = brand.toUpperCase().substring(0, 2) + uuid.toString().substring(length/2, length) + "-" + StringUtils.length(label) + "-" + StringUtils.length(description) + "-" + uuid.toString().substring(0, length/3);
				}

				List<String> productImages = new ArrayList<>();
				if (status.compareTo(TransformImageStatus.ERROR.getStatus()) != 0) {
					if (imageId != null) {
						productImages.add(imageId);
						File constructFileEntity = fileFactory.constructFileEntity(imageId, label,
								MediaType.IMAGE_JPEG_VALUE);
						fileDao.save(constructFileEntity);
					}
				}

				ProductCreateDto productDto = productMapper.toProductDto(model, label, description, refCategory, brand,
						productImages);
				products.put(id, productDto);
			} catch (Exception e) {
				return new ImportResponse(GeneralStatus.ERROR, ErrorCause.IMPORT_PRODUCT);
			}
		}

		while ((nextRecord = this.csvReaderHelper.getNextRecordOf(caractKey)) != null) {
			try {
				String productId = nextRecord[0];
				String label = nextRecord[1];
				String value = nextRecord[2];
				String valueMin = nextRecord[3];
				String valueMax = nextRecord[4];
				String unit = nextRecord[5];
				String type = nextRecord[6];

				CharacteristicDto characteristic = new CharacteristicDto();

				if (unit != null && !unit.isEmpty()) {
					UnitDto unitDto = new UnitDto();
					unitDto.setLabelUnit(unit);
					unitDto.setDescriptionUnit("@Automatic Comment");
					this.units.put(unit, unitDto);
					caracteristicService.createUnit(unitDto);
					characteristic.setTextFormatOnly(false);
				} else {
					characteristic.setTextFormatOnly(true);
				}

				characteristic.setLabel(label);

				if (type.equals(CaracteristicType.SIMPLE.getType())) {
					// simple
					if (!characteristic.isTextFormatOnly()) {
						value = value.replaceAll(",", "");
					}
					SimpleValue simpleValue = new SimpleValue(value);
					characteristic.setValue(valueFactory.makeJsonFromValue(simpleValue, CaractValueType.SIMPLE_VALUE));
					characteristic.setValueType(CaractValueType.SIMPLE_VALUE.getValueType());
				} else if (type.equals(CaracteristicType.MIN_MAX.getType())) {
					// min max
					valueMin = valueMin.replaceAll(",", "");
					valueMax = valueMax.replaceAll(",", "");
					MinMaxValue minMaxValue = new MinMaxValue(valueMin, valueMax);
					characteristic.setValue(valueFactory.makeJsonFromValue(minMaxValue, CaractValueType.MIN_MAX_VALUE));
					characteristic.setValueType(CaractValueType.MIN_MAX_VALUE.getValueType());
				} else if (type.equals(CaracteristicType.LIST.getType())) {
					// list
					String[] values = value.split(",");
					List<String> valueList = Arrays.asList(values);
					characteristic.setValue(
							valueFactory.makeJsonFromValue(new ListValue(valueList), CaractValueType.LIST_VALUE));
					characteristic.setValueType(CaractValueType.LIST_VALUE.getValueType());
				}
				characteristic.setUnit(unit);
				ProductCreateDto productCreateDto = products.get(productId);

				productCreateDto.addCharacteristic(characteristic);

			} catch (Exception e) {
				return new ImportResponse(GeneralStatus.ERROR, ErrorCause.IMPORT_CARACTERISTIC);
			}
		}

		ImportResponse response = new ImportResponse(GeneralStatus.SUCCESS);
		for (Map.Entry<String, ProductCreateDto> product : this.products.entrySet()) {
			try {
				productService.createProduct(product.getValue(), true, true);
			} catch (Exception e) {
				response.setStatus(GeneralStatus.WITH_ERRORS);
				response.addCause(ErrorCause.IMPORT_CARACTERISTIC);
				response.addCause(ErrorCause.IMPORT_PRODUCT);
			}
		}
		return response;

	}

	@Override
	public void initProducts() {
		List<Key<Product>> Nkeys = ObjectifyService.ofy().load().type(Product.class).keys().list();
		ObjectifyService.ofy().delete().keys(Nkeys).now();

		List<Key<Brand>> Fkeys = ObjectifyService.ofy().load().type(Brand.class).keys().list();
		ObjectifyService.ofy().delete().keys(Fkeys).now();

		List<Key<Unit>> Gkeys = ObjectifyService.ofy().load().type(Unit.class).keys().list();
		ObjectifyService.ofy().delete().keys(Gkeys).now();

		List<Key<Characteristic>> Ckeys = ObjectifyService.ofy().load().type(Characteristic.class).keys().list();
		ObjectifyService.ofy().delete().keys(Ckeys).now();

		List<Key<SimpleValue>> SVkeys = ObjectifyService.ofy().load().type(SimpleValue.class).keys().list();
		ObjectifyService.ofy().delete().keys(SVkeys).now();

		List<Key<MinMaxValue>> MNXkeys = ObjectifyService.ofy().load().type(MinMaxValue.class).keys().list();
		ObjectifyService.ofy().delete().keys(MNXkeys).now();

		List<Key<ListValue>> LSTkeys = ObjectifyService.ofy().load().type(ListValue.class).keys().list();
		ObjectifyService.ofy().delete().keys(LSTkeys).now();

		List<Key<File>> Xkeys = ObjectifyService.ofy().load().type(File.class).keys().list();
		ObjectifyService.ofy().delete().keys(Xkeys).now();
	}

	public boolean isProcessForCron() {
		return processForCron;
	}

	public void setProcessForCron(boolean processForCron) {
		this.processForCron = processForCron;
	}
}
