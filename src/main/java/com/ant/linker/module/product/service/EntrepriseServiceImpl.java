package com.ant.linker.module.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ant.linker.module.shared.mapper.IProductMapper;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {
	
	@Autowired
	IProductMapper productMapper;

}
