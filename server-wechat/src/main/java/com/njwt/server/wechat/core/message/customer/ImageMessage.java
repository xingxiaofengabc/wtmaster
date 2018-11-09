package com.njwt.server.wechat.core.message.customer;

public class ImageMessage extends BaseMessage {
	private Image image;

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

}
