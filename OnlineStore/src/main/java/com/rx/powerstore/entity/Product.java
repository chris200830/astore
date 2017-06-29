package com.rx.powerstore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({@NamedQuery(name="Product.findByCategoryName", query = "select p from Product p, Category c where p.category.id = c.id and c.name=(?1)"),
@NamedQuery(name="Product.findByCategoryNameOrderByPriceAsc", query = "select p from Product p, Category c where p.category.id = c.id and c.name=(?1) order by p.price asc"),
@NamedQuery(name="Product.findByCategoryNameAndManufacturer", query = "select p from Product p, Category c where p.category.id = c.id and c.name=(?1) and p.manufacturer=(?2)")})

@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@Lob
	@Column
	private String details;

	@Column(name = "price", columnDefinition = "Decimal(10,2)")
	private float price;

	@ManyToOne
	private Category category;

	private int totalCount;
	
	private String manufacturer;
	
	private String sizeList;
	
	private String thumbnailPath;

	@OneToMany(targetEntity = Thumbnail.class, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Thumbnail> thumbnails;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Thumbnail> getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(List<Thumbnail> thumbnails) {
		this.thumbnails = thumbnails;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSizeList() {
		return sizeList;
	}

	public void setSizeList(String sizeList) {
		this.sizeList = sizeList;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
}
