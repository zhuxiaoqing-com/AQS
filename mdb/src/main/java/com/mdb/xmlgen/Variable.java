package com.mdb.xmlgen;

import com.mdb.utils.ElementHelper;
import com.mdb.utils.StringUtils;
import com.mdb.xmlgen.type.Type;
import com.mdb.xmlgen.type.TypeName;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Variable extends Type {
	private final static Pattern pattern = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");
	private final static Set<String> keywords = new HashSet<>(Arrays.asList("Object", "abstract", "add", "alias", "and",
			"and_eq", "as", "ascending", "asm", "assert", "async", "auto", "await", "base", "bitand", "bitor", "bool",
			"boolean", "break", "byte", "case", "catch", "char", "checked", "class", "compl", "const", "const_cast",
			"continue", "decimal", "default", "delegate", "delete", "descending", "do", "double", "dynamic",
			"dynamic_cast", "else", "enum", "event", "explicit", "export", "extends", "extern", "false", "final",
			"finally", "fixed", "float", "for", "foreach", "friend", "get", "global", "goto", "group", "if",
			"implements", "implicit", "import", "in", "inline", "instanceof", "int", "interface", "internal", "into",
			"is", "join", "let", "lock", "long", "mutable", "namespace", "native", "new", "not", "not_eq", "null",
			"object", "operator", "or", "or_eq", "orderby", "out", "override", "package", "params", "partial",
			"private", "protected", "public", "readonly", "ref", "register", "reinterpret_cast", "remove", "return",
			"sbyte", "sealed", "select", "set", "short", "signed", "sizeof", "stackalloc", "static", "static_cast",
			"strictfp", "string", "struct", "super", "switch", "synchronized", "template", "this", "throw", "throws",
			"transient", "true", "try", "typedef", "typeid", "typename", "typeof", "uint", "ulong", "unchecked",
			"union", "unsafe", "unsigned", "ushort", "using", "value", "var", "virtual", "void", "volatile", "wchar_t",
			"where", "while", "xor", "xor_eq", "yield",
			// c++ class method names
			"marshal", "unmarshal", "trace", "encode", "getType", "process", "destroy", "equals", "Equals"));

	static void verifyName(String nm) {
		String name = nm;
		if (!pattern.matcher(name).matches())
			throw new RuntimeException("invalid name \"" + name + "\"");
		if (keywords.contains(name))
			throw new RuntimeException("variable reserved keyword \"" + name + "\"");
	}

	private String type;

	private String key = "";

	private String value = "";

	private String defautValue = "";

	private String comment = "";

	private Type varType;

	private Xbean xbean;

	private Element self;
	
	private int alias = -1;

	private Set<Integer> aliasSet;
	MdbMetadata mdbMetadata;
	public Variable(Xbean xbean, Element self,Set<Integer> aliasSet) {
		super(self);
		this.xbean = xbean;
		this.self = self;
		this.aliasSet = aliasSet;
		this.mdbMetadata = xbean.getMdbMetadata();
		initialize(self);
	}

	private void initialize(Element self) {
		verifyName(this.getName());
		ElementHelper eh = new ElementHelper(self);
		this.type = eh.getString("type");
		this.key = eh.getString("key");
		this.value = eh.getString("value");
		this.defautValue = eh.getString("default");
		this.alias = eh.getInt("alias");
		if(this.aliasSet.contains(this.alias)){
			throw new RuntimeException("alias is repeat name:" + getName());
		}
		this.aliasSet.add(alias);
		this.comment = extractComment(self);
		this.varType = TypeName.parseType(mdbMetadata, type, key, value);
	}

	public static String extractComment(Element self) {
		String comment = "";
		Node c = self.getNextSibling();
		if (c != null && Node.TEXT_NODE == c.getNodeType()) {
			comment = c.getTextContent().trim().replaceAll("[\r\n]", "");
		}
		if (!comment.isEmpty())
			comment = " // " + comment;
		return comment;
	}

	public static Pattern getPattern() {
		return pattern;
	}

	public static Set<String> getKeywords() {
		return keywords;
	}

	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getDefautValue() {
		return defautValue;
	}

	public String getComment() {
		return comment;
	}

	public Type getVarType() {
		return varType;
	}

	public Xbean getXbean() {
		return xbean;
	}

	public Element getSelf() {
		return self;
	}

	public int getAlias() {
		return alias;
	}

	@Override
	public String toVarType() {
		if(StringUtils.isEmpty(defautValue)) {
			return String.format("private %s %s; ", varType.toVarType(), getName());
		}else {
			return String.format("private %s %s = %s; ", varType.toVarType(), getName(), varType.toDefault(defautValue));
		}
	}

	@Override
	public String toInitVal() {
		return String.format("this.%s = %s", getName(), getVarType().toInitVal());
	}

	@Override
	public String toCopy(Type type) {
		return String.format("this.%s = %s", getName(), varType.toCopy(this));
	}

	@Override
	public boolean isConstType() {
		return false;
	}
}
