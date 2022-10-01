package com.grovs.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.grovs.dao.IAdminDao;
import com.grovs.dao.ICategoryDao;
import com.grovs.dao.IProductDao;
import com.grovs.dao.IRootCategoryDao;
import com.grovs.dao.ProductDao;
import com.grovs.model.Category;
import com.grovs.model.Product;
import com.grovs.model.RootCategory;

@Service
public class AdminService implements IAdminService {
	@Autowired
	private IAdminDao adminDaoObject;
	private Product product=new Product();
	@Autowired
	private IProductDao productDaoObject;
	@Autowired
	private ICategoryDao categoryDaoObject;
	@Autowired
	private IRootCategoryDao rootCategoryDaoObject;

	public void saveProduct(String name, double basePrice, int category, String description, double discount,
			double gst, MultipartFile multipartFile, int returnPeriod, int stock) {
		// TODO Auto-generated method stub
		product.setName(name);
		product.setBasePrice(basePrice);
		product.setCategoryId(category);
		product.setDescription(description);
		product.setDiscount(discount);
		product.setReturnPeriod(returnPeriod);
		product.setStock(stock);
		product.setUpdatedTime(LocalDateTime.now());
		
		String fileName=multipartFile.getOriginalFilename();
		String filePath=null;
		System.out.println(fileName);
		if(fileName.contains("..")) 
		{
//					throw new fileNameException()
		}
		else {
			Category categoryObject=categoryDaoObject.findById(category).orElse(null);
			RootCategory rootCategoryObject=rootCategoryDaoObject.findById(categoryObject.getRootCategoryId()).orElse(null);
			
			filePath="../../images/"+rootCategoryObject.getName()+"/"+categoryObject.getName()+"/"+fileName;
			System.out.println(filePath);
			System.out.println();
			product.setImage(filePath);
			productDaoObject.save(product);
			
		}
		
		
	}

	public void saveImage(int category, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		String fileName=multipartFile.getOriginalFilename();
		System.out.println(fileName);
		String directoryPath=null;
		if(fileName.contains("..")) 
		{
//					throw new fileNameException()
		}
		else {
			Category categoryObject=categoryDaoObject.findById(category).orElse(null);
			RootCategory rootCategoryObject=rootCategoryDaoObject.findById(categoryObject.getRootCategoryId()).orElse(null);
			Path currentRelativePath = Paths.get("");
			Path s = currentRelativePath.toAbsolutePath();
			System.out.println(s);
			directoryPath=s+"/src/main/resources/static/images/"+rootCategoryObject.getName()+"/"+categoryObject.getName();
			System.out.println(directoryPath);
			Path path=Paths.get(directoryPath);
			File file=new File(directoryPath);
			if(!file.exists()) {
				try {
					file.createNewFile();
					System.out.println(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try(InputStream inputStream=multipartFile.getInputStream()){
				Path filePath=path.resolve(fileName);
				System.out.println(filePath);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			}
			catch (IOException ioe) {        
//	            throw new IOException("Could not save image file: " + fileName, ioe);
				ioe.printStackTrace();
	        } 
				
		}
	}

}
