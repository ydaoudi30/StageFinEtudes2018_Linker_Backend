package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index
public class GeneralCatalog implements Serializable {

	@Id
	private Long idGenralCatalog;
	@Load
	private List<Ref<AbstractCategory>> listeAbstractCategory;
	
	

	public Long getIdGenralCatalog() {
		return idGenralCatalog;
	}

	public void setIdGenralCatalog(Long idGenralCatalog) {
		this.idGenralCatalog = idGenralCatalog;
	}

	public void setListeAbstractCategory(List<AbstractCategory> liste) {
		for (AbstractCategory abstractCategory : liste) {

			listeAbstractCategory.add(Ref.create(abstractCategory));

		}
	}

	public void addAbstractCategory(AbstractCategory abstractCategory) {

		listeAbstractCategory.add(Ref.create(abstractCategory));

	}
	
	
	public void removeAbstractCategoty(AbstractCategory abstractCategory){
		
		listeAbstractCategory.remove(Ref.create(abstractCategory));
		
	}
	

	public GeneralCatalog() {
		super();
		this.listeAbstractCategory = new ArrayList<>();
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
	public String toString() {
		return "GeneralCataloge [idGenralCatalog=" + idGenralCatalog + ", listeAbstractCategory="
				+ listeAbstractCategory + "]";
	}

}