package org.jboss.tools.gwt.shared;

import com.google.gwt.user.client.rpc.IsSerializable;


public class Email implements IsSerializable{
		
	
			private int iD;
			private String address;
			public int getiD() {
				return iD;
			}
			public void setiD(int iD) {
				this.iD = iD;
			}
			public String getAddress() {
				return address;
			}
			public void setAddress(String address) {
				this.address = address;
			}
			public int getClientiD() {
				return clientiD;
			}
			public void setClientiD(int clientiD) {
				this.clientiD = clientiD;
			}
			public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
			public int getUseriD() {
				return useriD;
			}
			public void setUseriD(int useriD) {
				this.useriD = useriD;
			}
			private int clientiD;
			private String message;
			private int useriD;


}
