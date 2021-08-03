package com.ant.linker.configuration;
import org.springframework.stereotype.Component;

import com.ant.linker.data.entity.AbstractCategory;
import com.ant.linker.data.entity.Brand;
import com.ant.linker.data.entity.CacheElement;
import com.ant.linker.data.entity.Cart;
import com.ant.linker.data.entity.Characteristic;
import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCart;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.Customer;
import com.ant.linker.data.entity.Discussion;
import com.ant.linker.data.entity.Entreprise;
import com.ant.linker.data.entity.Event;
import com.ant.linker.data.entity.File;
import com.ant.linker.data.entity.FinalCategory;
import com.ant.linker.data.entity.GeneralCatalog;
import com.ant.linker.data.entity.Guest;
import com.ant.linker.data.entity.ImportConfig;
import com.ant.linker.data.entity.Message;
import com.ant.linker.data.entity.NodeCategory;
import com.ant.linker.data.entity.Product;
import com.ant.linker.data.entity.Quotation;
import com.ant.linker.data.entity.QuotationRequest;
import com.ant.linker.data.entity.QuoteRequestInfo;
import com.ant.linker.data.entity.QuoteResponseInfo;
import com.ant.linker.data.entity.Unit;
import com.ant.linker.data.entity.User;
import com.ant.linker.data.entity.event.type.AbstractEventContent;
import com.ant.linker.data.entity.event.type.NewMessageQuoteEventContent;
import com.ant.linker.data.entity.event.type.NewQuoteEventContent;
import com.ant.linker.data.entity.event.type.QuoteEventContent;
import com.ant.linker.data.entity.values.type.ListValue;
import com.ant.linker.data.entity.values.type.MinMaxValue;
import com.ant.linker.data.entity.values.type.SimpleValue;
import com.googlecode.objectify.ObjectifyService;

@Component
public class EntitiesRegistrer {
	
	public EntitiesRegistrer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void registrEntities(){
		ObjectifyService.register(User.class);	
		ObjectifyService.register(Product.class);	
		ObjectifyService.register(File.class);	
		ObjectifyService.register(Brand.class);	
		ObjectifyService.register(Entreprise.class);
		ObjectifyService.register(Commercial.class);	
		ObjectifyService.register(CommercialCatalog.class);
		ObjectifyService.register(Quotation.class);
		ObjectifyService.register(QuotationRequest.class);
		ObjectifyService.register(QuoteRequestInfo.class);
		ObjectifyService.register(QuoteResponseInfo.class);
		ObjectifyService.register(Discussion.class);
		ObjectifyService.register(Message.class);
		ObjectifyService.register(Customer.class);
		ObjectifyService.register(GeneralCatalog.class);
		ObjectifyService.register(AbstractCategory.class);
		ObjectifyService.register(FinalCategory.class);
		ObjectifyService.register(NodeCategory.class);
		ObjectifyService.register(Unit.class);
		ObjectifyService.register(Characteristic.class);
		ObjectifyService.register(Guest.class);
		ObjectifyService.register(Cart.class);
		ObjectifyService.register(CommercialCart.class);
		ObjectifyService.register(SimpleValue.class);
		ObjectifyService.register(MinMaxValue.class);
		ObjectifyService.register(ListValue.class);
		ObjectifyService.register(Event.class);
		ObjectifyService.register(AbstractEventContent.class);
		ObjectifyService.register(NewQuoteEventContent.class);
		ObjectifyService.register(NewMessageQuoteEventContent.class);
		ObjectifyService.register(QuoteEventContent.class);
		ObjectifyService.register(CacheElement.class);
		ObjectifyService.register(ImportConfig.class);
	}
}