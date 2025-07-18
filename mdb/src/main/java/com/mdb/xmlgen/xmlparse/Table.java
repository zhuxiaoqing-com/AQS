package com.mdb.xmlgen.xmlparse;

import com.mdb.utils.ElementHelper;
import com.mdb.xmlgen.Naming;
import com.mdb.xmlgen.type.Type;
import com.mdb.xmlgen.type.TypeName;
import org.w3c.dom.Element;

import java.util.HashMap;
import java.util.Map;

public class Table extends Naming {

	private String key;

	private String value;

	private boolean autoIncrement;

	private Map<String, String> otherAttrs = new HashMap<String, String>();

	private int cacheCapValue;

	private Type keyType;

	private Type valueType;

	private boolean supportFlush;

	private MdbMetadata mdbMetadata;


	public Table() {
	}

	public Table(MdbMetadata mdbMetadata, Element self, boolean supportFlush) {
		super(self);
		this.mdbMetadata = mdbMetadata;
		String name = getName();
		if (name.isEmpty())
			throw new RuntimeException("table name MUST NOT empty");
		if (!name.toLowerCase().equals(name))
			throw new RuntimeException("table name MUST BE lowercase");
		ElementHelper eh = new ElementHelper(self);
		key = eh.getString("key");
		value = eh.getString("value");
		autoIncrement = "true".equalsIgnoreCase(eh.getString("autoIncrement"));
		cacheCapValue = Integer.valueOf(eh.getString("cacheCapacity"));
		otherAttrs = eh.getUnused("name");
		if (getName().equalsIgnoreCase("_Meta_"))
			throw new RuntimeException("Table name \"" + getName() + "\" is not permitted.");
		if (getName().equalsIgnoreCase("_Tables_"))
			throw new RuntimeException("Table name \"" + getName() + "\" is not permitted.");
		keyType = TypeName.parseType(mdbMetadata, key, "", "");
		valueType = TypeName.parseType(mdbMetadata, value, "", "");
		// table value必须是xbean
		if (!valueType.isXbean()) {
			throw new RuntimeException("Table name \"" + getName() + "\"  value must be Xbean");
		}
		String ssf = eh.getString("supportFlush");
		if(ssf != null && ssf.length() > 0) {
			// 优先使用table上配置的，如果没有才用mdb上面配置的
			this.supportFlush = Boolean.parseBoolean(ssf);
		}else {
			this.supportFlush = supportFlush;
		}
		
	}

	public boolean isSupportFlush() {
		return supportFlush;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getFirstName() {
		return "xtable." + mdbMetadata.getName();
	}

	public String getFullName() {
		return getFirstName() + "." + getName();
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public Map<String, String> getOtherAttrs() {
		return otherAttrs;
	}

	public int getCacheCapValue() {
		return cacheCapValue;
	}

	public Type getKeyType() {
		return keyType;
	}

	public Type getValueType() {
		return valueType;
	}

}
