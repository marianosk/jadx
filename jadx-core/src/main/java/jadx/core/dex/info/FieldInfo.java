package jadx.core.dex.info;

import jadx.core.dex.instructions.args.ArgType;
import jadx.core.dex.nodes.DexNode;

import com.android.dex.FieldId;

public class FieldInfo {

	private final ClassInfo declClass;
	private final String name;
	private final ArgType type;

	public FieldInfo(ClassInfo declClass, String name, ArgType type) {
		this.declClass = declClass;
		this.name = name;
		this.type = type;
	}

	public static FieldInfo fromDex(DexNode dex, int index) {
		FieldId field = dex.getFieldId(index);
		return new FieldInfo(
				ClassInfo.fromDex(dex, field.getDeclaringClassIndex()),
				dex.getString(field.getNameIndex()),
				dex.getType(field.getTypeIndex()));
	}

	public String getName() {
		return name;
	}

	public ArgType getType() {
		return type;
	}

	public ClassInfo getDeclClass() {
		return declClass;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		FieldInfo fieldInfo = (FieldInfo) o;
		return name.equals(fieldInfo.name)
				&& type.equals(fieldInfo.type)
				&& declClass.equals(fieldInfo.declClass);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + declClass.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return declClass + "." + name + " " + type;
	}
}
