package com.ant.linker.module.product.importing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.linker.data.dao.IAbstractCategoryDao;
import com.ant.linker.data.entity.AbstractCategory;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.GeneralCatalog;
import com.ant.linker.data.entity.NodeCategory;
import com.ant.linker.module.shared.dto.product.CategoryDto;
import com.ant.linker.module.shared.factory.IGeneralCatalogFactory;
import com.ant.linker.module.shared.mapper.CategoryMapper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

@Service
public class CategoryImporterImpl implements ICategoryImporter {
	private List<CategoryDto> categories;
	private GeneralCatalog generalCatalog;

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private IAbstractCategoryDao categoryDao;
	@Autowired
	private IGeneralCatalogFactory generalCatalogFactory;
	@Autowired
	private ICSVReaderHelper csvReaderHelper;

	public CategoryImporterImpl() {
		super();
		this.categories = new ArrayList<>();
	}

	@Override
	public void execute() {

		initCatalog();
		this.csvReaderHelper.addFileToLoad(ImportConstants.CATEGORY_KEY, ImportConstants.CATEGORIES_FILE_PATH);

		String[] nextRecord;
		while ((nextRecord = this.csvReaderHelper.getNextRecordOf(ImportConstants.CATEGORY_KEY)) != null) {
			String refCategory = nextRecord[0];
			String refParentCategory = nextRecord[1];
			String categoryName = nextRecord[4];

			CategoryDto extractedCategory = extractCategory(categoryName, refCategory);
			if (refParentCategory.isEmpty()) {
				categories.add(extractedCategory);
			} else {
				CategoryDto parentCategory = resolveParentCategory(refParentCategory, categories);
				if (parentCategory != null) {
					linkCategoryWithParent(parentCategory, extractedCategory);
				}
			}

		}

		this.handleFinalParam(categories);
		List<AbstractCategory> mappedCategories = categoryMapper.dtosToEntities(categories);

		this.generalCatalog = generalCatalogFactory.getInstance();
		generalCatalog.setListeAbstractCategory(mappedCategories);
		categoryDao.saveCatalog(generalCatalog);
	}

	public void initCatalog() {
		List<Key<NodeCategory>> Nkeys = ObjectifyService.ofy().load().type(NodeCategory.class).keys().list();
		ObjectifyService.ofy().delete().keys(Nkeys).now();

		List<Key<FinalCategory>> Fkeys = ObjectifyService.ofy().load().type(FinalCategory.class).keys().list();
		ObjectifyService.ofy().delete().keys(Fkeys).now();

		List<Key<GeneralCatalog>> Gkeys = ObjectifyService.ofy().load().type(GeneralCatalog.class).keys().list();
		ObjectifyService.ofy().delete().keys(Gkeys).now();
	}

	private CategoryDto resolveParentCategory(String refParentCategory, List<CategoryDto> subjectCategories) {
		CategoryDto resolvedParent = null;
		for (CategoryDto category : subjectCategories) {

			String ref = category.getRefCategory();
			List<CategoryDto> childs = category.getChilds();

			if (ref.compareTo(refParentCategory) == 0) {
				resolvedParent = category;
			} else if (!childs.isEmpty()) {
				resolvedParent = resolveParentCategory(refParentCategory, childs);
			}
		}
		return resolvedParent;
	}

	private CategoryDto extractCategory(String categoryName, String refCategory) {
		CategoryDto category = new CategoryDto();
		category.setLabel(categoryName);
		category.setRefCategory(refCategory);
		return category;
	}

	private void handleFinalParam(List<CategoryDto> subjectCategories) {
		for (CategoryDto category : subjectCategories) {
			List<CategoryDto> childs = category.getChilds();
			if (childs.isEmpty()) {
				category.setFinalCategory(true);
			} else {
				handleFinalParam(childs);
			}
		}
	}

	private void linkCategoryWithParent(CategoryDto parent, CategoryDto child) {
		parent.addCategoryDto(child);
	}

}
