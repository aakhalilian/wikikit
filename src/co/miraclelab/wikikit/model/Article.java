package co.miraclelab.wikikit.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Article extends Storable {
	private String title;
	private String content;
	private String key;
	@DBRef
	private Article parent;

	public Article getParent() {
		return parent;
	}

	public void setParent(Article parent) {
		this.parent = parent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
