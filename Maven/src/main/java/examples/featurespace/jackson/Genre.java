package examples.featurespace.jackson;

import com.google.gson.annotations.SerializedName;

public enum Genre {
	@SerializedName("0")
	DRAMA,
	@SerializedName("1")
	CRIME,
	@SerializedName("2")
	ADVENTURE,
	@SerializedName("3")
	ACTION,
	@SerializedName("4")
	FANTASY,
	@SerializedName("5")
	SCIENCE_FICTION,
	@SerializedName("6")
	HORROR,
	@SerializedName("7")
	FAMILY,
	@SerializedName("8")
	MUSICAL,
	@SerializedName("9")
	SUSPENSE,
	@SerializedName("10")
	COMEDY,
	@SerializedName("11")
	ANIMATED,
	@SerializedName("12")
	OTHER

}
