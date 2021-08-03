package com.ant.linker.data.dao;



import com.ant.linker.data.entity.Commercial;
import com.ant.linker.data.entity.CommercialCatalog;
import com.ant.linker.data.entity.User;



public interface ICommercial {

	public Commercial loadCommercialByUser(User user);

	public Commercial saveCommercial(Commercial commercial);


}
