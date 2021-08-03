package com.ant.linker.data.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.AbstractCategory;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.GeneralCatalog;
import com.ant.linker.data.entity.NodeCategory;

@Component
public class AbstractCategoryDao implements IAbstractCategoryDao {

	@Override
	public List<AbstractCategory> loadFromGeneralCatalog() {
		// TODO Auto-generated method stub
		GeneralCatalog generalCatalog = ofy().load().type(GeneralCatalog.class).first().now();
		if(generalCatalog != null){
			return generalCatalog.getListeAbstractCategory();
		}
		return null;
	}

	@Override
	public FinalCategory loadFinalCategoryByref(String refCategory) {
		// TODO Auto-generated method stub
		return ofy().load().type(FinalCategory.class).filter("ref =", refCategory).first().now();
	}
	
	@Override
	public AbstractCategory saveCategory(AbstractCategory category) {
		AbstractCategory foundCategory = ofy().load().type(AbstractCategory.class).filter("ref =", category.getRef()).first().now();
		if(foundCategory != null) {
			foundCategory.setLabel(category.getLabel());
			ofy().save().entity(foundCategory).now();
		} else {
			ofy().save().entity(category).now();
		}
		return category;
	}
	
	@Override
	public GeneralCatalog saveCatalog(GeneralCatalog catalog){
		ofy().save().entity(catalog).now();
		return catalog;
	}

	@Override
	public NodeCategory loadNodeCategoryByChildRef(AbstractCategory childCategory) {
		return ofy().load().type(NodeCategory.class).filter("listeAbstractCategory", childCategory).first().now();
	}
}
