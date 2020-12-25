package com.coffeemachine.interfaces;

import java.util.List;

import com.coffeemachine.models.Product;

public interface ProductDao {

	public void AddProduct(List<Product> products,Product product) throws Exception;
}
