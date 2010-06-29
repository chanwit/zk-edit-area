window.eachangecallback = function(id) {
	var wgt = zk.Widget.$(id);
	var content = wgt.$n('cnt');
	if (!content){		
		return;
	}		
	var val = editAreaLoader.getValue(content.id);		
	wgt.fire('onChange', {value: val}, {sendAhead: true});
}

editarea.Editarea = zk.$extends(zul.Widget,{
	_height: '200px',
	_width: '600px',
	_syntax: 'java',
	_language: 'en',
	_value: '',
	$define: {
		height: function(v){
			var content = this.$n('cnt');
			if (content){
				this.rerender();
			} 				
		},
		width: function(v){
			var content = this.$n('cnt');
			if (content){
				this.rerender();	
			} 				
		},
		syntax: function(v){
			var content = this.$n('cnt');
			if (content){
				editAreaLoader.init({
					id: content.id, // textarea id
					syntax: v // syntax to be uses for highgliting			
				});				
			} 				
		},
		language: function(v){
			var content = this.$n('cnt');
			if (content){
				editAreaLoader.init({
					id: content.id, // textarea id
					language: v 			
				});				
			} 				
		},
		
		value: function(v){
			var content = this.$n('cnt');
			if (content){
				editAreaLoader.setValue(content.id, v);	
			} 									
		},	
	},
	bind_: function(){
		this.$supers('bind_', arguments);
		var content = this.$n('cnt'),area = this.$n();
					
		editAreaLoader.init({
			id: content.id, // textarea id
			syntax: this._syntax, // syntax to be uses for highlighting
			language: this._language,
			start_highlight: true,
			allow_toggle: false,
			min_width: jq(area).innerWidth(),
			min_height: jq(area).innerHeight(),
			change_callback: "eachangecallback"
			
		});
		editAreaLoader.setValue(content.id, this._value);
				
	},
	redraw: function(out){
		//zk.log("this.domAttrs_():",this.domAttrs_());
		out.push('<div ',this.domAttrs_(),'><textarea id="', this.uuid, '-cnt" name="content" cols="1" rows="1" style="visibility:hidden;"></textarea></div>');
	},
}
);

