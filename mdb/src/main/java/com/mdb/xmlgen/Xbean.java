package com.mdb.xmlgen;

import com.mdb.util.ElementHelper;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.type.Type;
import com.mdb.xmlgen.xmlparse.Enum;
import com.mdb.xmlgen.xmlparse.MdbMetadata;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Xbean extends Type {

	private String name;

	private String comment;

	private Element self;

	private List<Variable> variables = new ArrayList<>();

	private List<com.mdb.xmlgen.xmlparse.Enum> enums = new ArrayList<>();

	private Set<String> varNameChecker = new HashSet<>();

	private Set<Integer> aliasSet = new HashSet<>();
	private MdbMetadata mdbMetadata;
	
	public Xbean(MdbMetadata mdbMetadata, Element self) {
		super(self);
		this.mdbMetadata = mdbMetadata;
		this.self = self;
		ElementHelper eh = new ElementHelper(self);
		this.name = eh.getString("name");
		this.aliasSet = new HashSet<>();
		XMLUtils.getChildElements(self).forEach(e -> {

			if (e.getNodeName().equals("enum")) {
				com.mdb.xmlgen.xmlparse.Enum xenum = new com.mdb.xmlgen.xmlparse.Enum(this, e);
				if (varNameChecker.add(xenum.getName()) == false) {
					// 重复
					throw new RuntimeException("Duplicate enum name in " + getName());
				}
				enums.add(xenum);
			} else if (e.getNodeName().equals("variable")) {
				Variable variable = new Variable(this, e,aliasSet);
				if (varNameChecker.add(variable.getName()) == false) {
					// 重复
					throw new RuntimeException("Duplicate bean name in " + getName() + " variable name in " + variable.getName());
				}
				variables.add(variable);
			}

		});
		this.comment = extractComment(self);
	}

	static String extractComment(Element self) {
		for (Node c = self.getPreviousSibling(); null != c; c = c.getPreviousSibling()) {
			if (Node.ELEMENT_NODE == c.getNodeType())
				break;
			if (Node.COMMENT_NODE == c.getNodeType())
				return "/** " + c.getTextContent().trim() + " **/";
			if (Node.CDATA_SECTION_NODE == c.getNodeType())
				return "/**" + c.getTextContent() + "*/";
		}
		return "";
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return "xbean." + mdbMetadata.getName();
	}

	public String getFullName() {
		return getFirstName() + "." + getName();
	}

	public String getComment() {
		return comment;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public Set<String> getVarNameChecker() {
		return varNameChecker;
	}

	public List<Enum> getEnums() {
		return enums;
	}

	public MdbMetadata getMdbMetadata() {
		return mdbMetadata;
	}

	@Override
	public boolean isConstType() {
		return false;
	}

	@Override
	public String toVarType() {
		return getFullName();
	}

	@Override
	public String toInitVal() {
		return String.format("new %s(this);", getName());
	}

	@Override
	public String toCopy(Type type) {
		return String.format("new %s(_o_.%s, this);", getName(), type.getName());
	}


}
