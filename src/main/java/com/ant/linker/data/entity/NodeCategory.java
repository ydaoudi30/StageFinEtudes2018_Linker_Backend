package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Index
@Entity

public class NodeCategory extends AbstractCategory implements Serializable {

	@Load
	private List<Ref<AbstractCategory>> listeAbstractCategory;
	
	public NodeCategory() {
		super();
		listeAbstractCategory = new ArrayList<>();
	}

	public void addAbstractCategory(AbstractCategory abstractCategory) {

		listeAbstractCategory.add(Ref.create(abstractCategory));

	}

	public void removeAbstractCategoty(AbstractCategory abstractCategory) {

		listeAbstractCategory.remove(Ref.create(abstractCategory));

	}

	public void setListeAbstractCategory(List<AbstractCategory> liste) {
		listeAbstractCategory = new ArrayList<>();
		for (AbstractCategory abstractCategory : liste) {

			listeAbstractCategory.add(Ref.create(abstractCategory));

		}
	}

	public List<AbstractCategory> getListeAbstractCategory() {

		List<AbstractCategory> abstractCategories = new ArrayList<>();

		for (Ref<AbstractCategory> refAbsCatego : listeAbstractCategory) {

			AbstractCategory abstractCategory = refAbsCatego.get();

			abstractCategories.add(abstractCategory);
		}

		return abstractCategories;

	}

	@Override
	public boolean isFinal() {
		return false;
	}

}
