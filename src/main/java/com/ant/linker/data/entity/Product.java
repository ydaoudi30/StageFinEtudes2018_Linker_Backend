package com.ant.linker.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ant.linker.data.entity.values.type.AbstractValue;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
@Index
public class Product implements Serializable, Cloneable {
	@Id
	private Long idProduct;
	private String label;
	private String model;
	private Date datePub;
	private String description;

	private String searchField;
	@Ignore
	private int searchScore;

	
	private List<Ref<CommercialCatalog>> listCommercialCatalog;

	@Load
	private List<Ref<Characteristic<AbstractValue>>> listeCharacteristic;
	@Load
	private List<Ref<File>> listImages;
	@Load
	private List<Ref<File>> listDocument;
	@Load
	private Ref<FinalCategory> finalCategory;
	@Load
	private Ref<Brand> brand;
	
	private List<Ref<QuoteRequestInfo>> listQuoteRequestInfo;

	public Product(String label, String model, Date datePub, String description) {
		super();
		this.label = label;
		this.model = model;
		this.datePub = datePub;
		this.description = description;
		this.listCommercialCatalog = new ArrayList<>();
		this.listeCharacteristic = new ArrayList<>();
		this.listImages = new ArrayList<>();
		this.listQuoteRequestInfo = new ArrayList<>();
	}

	public String makeSearchField() {
		return this.searchField = model + " " + label + " " + getBrand().getLabel() + " " + description;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
		this.listCommercialCatalog = new ArrayList<>();
		this.listeCharacteristic = new ArrayList<>();
		this.listImages = new ArrayList<>();
		this.listQuoteRequestInfo = new ArrayList<>();
	}

	public void addCharacteristic(Characteristic characteristic) {

		listeCharacteristic.add(Ref.create(characteristic));

	}

	public void removeCharacteristic(Characteristic characteristic) {

		listeCharacteristic.remove(Ref.create(characteristic));

	}

	public void addCommercialCatalog(CommercialCatalog commercialCatalog) {

		listCommercialCatalog.add(Ref.create(commercialCatalog));

	}

	public void removeCommercialCatalog(CommercialCatalog commercialCatalog) {

		listCommercialCatalog.remove(Ref.create(commercialCatalog));

	}

	public void setListDocument(List<File> liste) {
		for (File document : liste) {
			listDocument.add(Ref.create(document));
		}

	}

	public void addDocument(File document) {
		listDocument.add(Ref.create(document));
	}

	public void removeDocument(File document) {
		listDocument.remove(Ref.create(document));
	}

	public List<File> getListDocument() {
		List<File> documents = new ArrayList<>();
		for (Ref<File> refDoc : listDocument) {
			File document = refDoc.get();
			documents.add(document);
		}
		return documents;
	}

	public void addAllInListImages(List<File> liste) {
		for (File image : liste) {
			listImages.add(Ref.create(image));
		}
	}
	
	public void setListImages(List<File> liste) {
		listImages = new ArrayList<>();
		for (File image : liste) {
			listImages.add(Ref.create(image));
		}
	}

	public void addImage(File image) {
		listImages.add(Ref.create(image));
	}

	public void removeImage(File image) {
		listImages.remove(Ref.create(image));
	}

	public List<File> getListeImages() {
		List<File> images = new ArrayList<>();
		for (Ref<File> refImg : listImages) {
			File image = refImg.get();
			images.add(image);
		}
		return images;
	}

	public File getFirstImage() {
		if (listImages != null && !listImages.isEmpty())
			return listImages.get(0).get();
		return null;
	}

	public String getSearchField() {
		return searchField;
	}

	public FinalCategory getFinalCategory() {
		return finalCategory.get();
	}

	public void setFinalCategory(FinalCategory finalCategory) {
		this.finalCategory = Ref.create(finalCategory);
	}

	public Brand getBrand() {

		return brand.get();
	}

	public void setBrand(Brand brand) {
		this.brand = Ref.create(brand);
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getDatePub() {
		return datePub;
	}

	public void setDatePub(Date datePub) {
		this.datePub = datePub;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CommercialCatalog> getlistCommercialCatalog() {
		List<CommercialCatalog> commercialCatalogs = new ArrayList<>();

		for (Ref<CommercialCatalog> refComCat : listCommercialCatalog) {

			CommercialCatalog commercialCatalog = refComCat.get();

			commercialCatalogs.add(commercialCatalog);

		}
		return commercialCatalogs;

	}

	public void addAllInListCommercialCatalog(List<CommercialCatalog> liste) {
		for (CommercialCatalog commercialCatalog : liste) {
			listCommercialCatalog.add(Ref.create(commercialCatalog));
		}
	}

	public void setListCommercialCatalog(List<CommercialCatalog> liste) {
		listCommercialCatalog = new ArrayList<>();
		for (CommercialCatalog commercialCatalog : liste) {
			listCommercialCatalog.add(Ref.create(commercialCatalog));
		}
	}
	
	public List<Characteristic<AbstractValue>> getListeCharacteristic() {

		List<Characteristic<AbstractValue>> characteristics = new ArrayList<>();

		for (Ref<Characteristic<AbstractValue>> refCharacteristic : listeCharacteristic) {

			Characteristic<AbstractValue> characteristic = refCharacteristic.get();

			characteristics.add(characteristic);

		}
		return characteristics;

	}

	public List<QuoteRequestInfo> getQuoteRequestInfo() {
		List<QuoteRequestInfo> quotations = new ArrayList<>();
		for (Ref<QuoteRequestInfo> refquote : listQuoteRequestInfo) {
			QuoteRequestInfo quote = refquote.get();
			quotations.add(quote);
		}
		return quotations;
	}

	public void addAllInListQuoteRequestInfo(List<QuoteRequestInfo> liste) {
		for (QuoteRequestInfo QuoteRequestInfo : liste) {
			listQuoteRequestInfo.add(Ref.create(QuoteRequestInfo));
		}
	}
	
	public void setListQuoteRequestInfo(List<QuoteRequestInfo> liste) {
		listQuoteRequestInfo = new ArrayList<>();
		for (QuoteRequestInfo QuoteRequestInfo : liste) {
			listQuoteRequestInfo.add(Ref.create(QuoteRequestInfo));
		}
	}

	public void addQuoteRequestInfo(QuoteRequestInfo quote) {
		listQuoteRequestInfo.add(Ref.create(quote));
	}

	public void removeQuoteRequestInfo(QuoteRequestInfo quote) {
		listQuoteRequestInfo.remove(Ref.create(quote));
	}

	public void addAllInListeCharacteristic(List<Characteristic<AbstractValue>> liste) {
		for (Characteristic<AbstractValue> characteristic : liste) {
			listeCharacteristic.add(Ref.create(characteristic));
		}
	}

	public void setListeCharacteristic(List<Characteristic<AbstractValue>> liste) {
		listeCharacteristic = new ArrayList<>();
		for (Characteristic<AbstractValue> characteristic : liste) {
			listeCharacteristic.add(Ref.create(characteristic));
		}
	}
	
	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", label=" + label + ", model=" + model + ", datePub=" + datePub
				+ ", description=" + description + ", listeCommercial=" + listCommercialCatalog + ", listImages="
				+ listImages + ", listdocument=" + listDocument + ", brand=" + brand + "]";
	}

	public boolean isInStock() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public Object clone() {
	    Product product = null;
	    try {
	    	product = (Product) super.clone();
	    } catch(CloneNotSupportedException cnse) {
	      	cnse.printStackTrace(System.err);
	    }
	    return product;
	}

	public Integer getSearchScore() {
		return searchScore;
	}

	public void setSearchScore(Integer searchScore) {
		this.searchScore = searchScore;
	}

}
