package com.sales.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
//Entity attributes inside method names.. easy way to make queries
@Entity //declares an object (Product) as an entity that the database should be aware of
@Table(name="PRODUCTS") //describes details about entity like table name
public class Product {
	@Id //Identifier attribute for a primary key
	@GeneratedValue //Used with @Id  to generate a unique value for a field
	@Column(name="PID")
	private Long pId;
	
	@Column(name="PDESC") //describes more specfic details about the column in table.
	@NotBlank
	private String pDesc;
	
	@Column(name="QTYINSTOCK")
	@Min(value=0)
	private int qtyInStock;
	
	@OneToMany(mappedBy="prod")
	private List<Order> ordersForProduct = new ArrayList<Order>();

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	public int getQtyInStock() {
		return qtyInStock;
	}

	public void setQtyInStock(int qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	public List<Order> getOrdersForProduct() {
		return ordersForProduct;
	}

	public void setOrdersForProduct(List<Order> ordersForProduct) {
		this.ordersForProduct = ordersForProduct;
	}

}
