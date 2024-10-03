package com.mbc.team.product;

import java.util.ArrayList;

public interface ProductService  {


	ArrayList<ProductDTO> outa();

	void inserta(String fname1, String cat1, String cat2, String product, int price, String fname2);

	ProductDTO detail(int itemnum);

	ArrayList<ProductDTO> arbat(String cat2);

	ArrayList<ProductDTO> carbonbat(String cat2);

	ArrayList<ProductDTO> woodbat(String cat2);

	ArrayList<ProductDTO> pengobat(String cat2);

	ArrayList<ProductDTO> youthbat(String cat2);

	ArrayList<ProductDTO> trainingbat(String cat2);

	ArrayList<ProductDTO> batgrip(String cat2);

	ArrayList<ProductDTO> bataccessories(String cat2);


}
 