package co.miraclelab.wikikit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Storable {
	@Id
	public String id;
}
