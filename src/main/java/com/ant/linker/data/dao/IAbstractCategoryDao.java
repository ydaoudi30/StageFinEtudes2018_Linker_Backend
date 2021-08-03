package com.ant.linker.data.dao;

import java.util.List;

import com.ant.linker.data.entity.AbstractCategory;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.GeneralCatalog;
import com.ant.linker.data.entity.NodeCategory;
import com.googlecode.objectify.Ref;


public interface IAbstractCategoryDao {

	public List<AbstractCategory> loadFromGeneralCatalog();
    public FinalCategory loadFinalCategoryByref (String refCategory);
    public AbstractCategory saveCategory(AbstractCategory category);
    public GeneralCatalog saveCatalog(GeneralCatalog catalog);
    public NodeCategory loadNodeCategoryByChildRef(AbstractCategory childCategory);
    
}
