package com.reststeak.reststeak_app.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "orders")
@XmlRootElement
public class Orders {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "steak")
	private String steak;
	@Column(name = "temperature")
	private String temperature;
	@Column(name = "side")
	private String side;
	@Column(name = "sauce")
	private String sauce;
	@Column(name = "orderStatus")
	private String orderStatus;
	
	public int getId() {
		return id;
	}
	
	public String getSteak() {
		return steak;
	}
	
	public void setSteak(String steak) {
		this.steak = steak;
	}
	
	public String getTemperature() {
		return temperature;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	public String getSide() {
		return side;
	}
	
	public void setSide(String side) {
		this.side = side;
	}
	
	public String getSauce() {
		return sauce;
	}
	
	public void setSauce(String sauce) {
		this.sauce = sauce;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
