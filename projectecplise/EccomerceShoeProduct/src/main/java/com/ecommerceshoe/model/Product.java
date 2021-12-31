package com.ecommerceshoe.model;

import java.util.Date;
import java.util.Objects;

public class Product {
	
	private String brandName;
	private String brandType;
	private int brandSize;
	private String color;
	private double prices;
	private Date manufactureDate;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public int getBrandSize() {
		return brandSize;
	}

	public void setBrandSize(int brandSize) {
		this.brandSize = brandSize;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrices() {
		return prices;
	}

	public void setPrices(double prices) {
		this.prices = prices;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Product(String brandName, String brandType, int brandSize, String color, double prices,
			Date manufactureDate) {
		super();

		this.brandName = brandName;
		this.brandType = brandType;
		this.brandSize = brandSize;
		this.color = color;
		this.prices = prices;
		this.manufactureDate = manufactureDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brandName, brandSize, brandType, color, manufactureDate, prices);
	}

	@Override
	public String toString() {
		return "Product [brandName=" + brandName + ", brandType=" + brandType + ", brandSize=" + brandSize + ", color="
				+ color + ", prices=" + prices + ", manufactureDate=" + manufactureDate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(brandName, other.brandName) && brandSize == other.brandSize
				&& Objects.equals(brandType, other.brandType) && Objects.equals(color, other.color)
				&& Objects.equals(manufactureDate, other.manufactureDate)
				&& Double.doubleToLongBits(prices) == Double.doubleToLongBits(other.prices);
	}

}
