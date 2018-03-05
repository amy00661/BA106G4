package iii.menu.model;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class MenuNodeVO implements Serializable{
	private static final long serialVersionUID = 1L;
	String id;
	String text;
	String url;
	Boolean checked;
	LinkedHashSet<MenuNodeVO> children = new LinkedHashSet<>();
	
	public MenuNodeVO() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public LinkedHashSet<MenuNodeVO> getChildren() {
		return children;
	}
	
	public int getChildrenSize(){
		return this.children.size();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuNodeVO other = (MenuNodeVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
