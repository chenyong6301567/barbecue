package com.qiaoge.barbecue.utils.enums;

/**
 * @author cy
 * @Description 删除枚举
 * @date 2018年3月28日下午8:33:51 
 */
public enum DeleteFlag {

	NOT_DELETE("未删除", false, (byte) 0), DELETE("删除", true, (byte) 1);

	private DeleteFlag(String description, Boolean value, Byte status) {
		this.description = description;
		this.value = value;
		this.status = status;
	}

	private String description;
	private Boolean value;
	private Byte status;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

}
