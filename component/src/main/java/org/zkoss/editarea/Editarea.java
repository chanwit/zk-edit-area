package org.zkoss.editarea;

import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;

public class Editarea extends AbstractComponent{
	private String _height = "200px";
	private String _width = "600px";
	private String _syntax = "java";//"basic","brainfuck","c","coldfusion","cpp","css","html","java","js","pas","perl","php","python","robotstxt","ruby","sql","tsql","vb","xml"
	private String _value = "";
	private String _language="en";//"bg","cs","de","dk","en","eo","es","fi","fr","hr","it","ja","mk","nl","pl","pt","ru","sk","zh"
		
	static {
		addClientEvent(Editarea.class, Events.ON_CHANGE, CE_IMPORTANT|CE_REPEAT_IGNORE);
		addClientEvent(Editarea.class, Events.ON_CHANGING, CE_BUSY_IGNORE);
	}

	public String getHeight() {
		return _height;
	}
	
	public void setHeight(String height) {
		if (height != null && height.length() == 0)
			height = null;
		if (!Objects.equals(height, _height)) {
			_height = height;
			smartUpdate("height", height);
		}
	}

	public String getWidth() {
		return _width;
	}
	
	public void setWidth(String width) {
		if (width != null && width.length() == 0)
			width = null;
		if (!Objects.equals(width, _width)) {
			_width = width;
			smartUpdate("width", width);
		}
	}

	public String getLanguage() {
		return _language;
	}
	
	public void setLanguage(String language) {
		if (language != null && language.length() == 0)
			language = null;
		if (!Objects.equals(language, _language)) {
			_language = language;
			smartUpdate("language", _language);
		}
	}

	public String getSyntax() {
		return _syntax;
	}
	
	public void setSyntax(String syntax) {
		if (syntax != null && syntax.length() == 0)
			syntax = null;
		if (!Objects.equals(syntax, _syntax)) {
			_syntax = syntax;
			smartUpdate("syntax", _syntax);
		}
	}

	public String getValue() {
		return _value;
	}
	
	public void setValue(String value) {
		if (value != null && value.length() == 0)
			value = null;
		if (!Objects.equals(value, _value)) {
			_value = value;
			smartUpdate("value", value);
		}
	}

	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String cmd = request.getCommand();
		if (cmd.equals(Events.ON_CHANGE)) {
			InputEvent evt = InputEvent.getInputEvent(request); 			
			_value = evt.getValue();
			Events.postEvent(evt);
		} else if (cmd.equals(Events.ON_CHANGING)) {
			Events.postEvent(InputEvent.getInputEvent(request));
			
		} else
			super.service(request, everError);
	}
	
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);
	
		if (!"200px".equals(_height))
			render(renderer, "height", _height);
		
		if (!"600px".equals(_width))
			render(renderer, "width", _width);

		if (!"java".equals(_syntax))
			render(renderer, "syntax", _syntax);

		if (!"en".equals(_language))
			render(renderer, "language", _language);

		if (!"".equals(_value))
			render(renderer, "value", _value);
	}	
}
