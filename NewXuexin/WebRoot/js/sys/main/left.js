 function AccordionMenu(options) {
	this.config = {
		containerCls        : '.wrap-menu',                // 外层容器
		menuArrs            :  '',                         //  JSON传进来的数据
		type                :  'click',                    // 默认为click 也可以mouseover
		renderCallBack      :  null,                       // 渲染html结构后回调
		clickItemCallBack   : null                         // 每点击某一项时候回调
	};
	this.cache = {
		
	};
	this.init(options);
 }

 
 AccordionMenu.prototype = {

	constructor: AccordionMenu,

	init: function(options){
		this.config = $.extend(this.config,options || {});
		var self = this,
			_config = self.config,
			_cache = self.cache;
		
		// 渲染html结构
		$(_config.containerCls).each(function(index,item){
			
			self._renderHTML(item);

			// 处理点击事件
			self._bindEnv(item);
		});
	},
	_renderHTML: function(container){
		var self = this,
			_config = self.config,
			_cache = self.cache;
		var ulhtml = $('<ul></ul>');
		$(_config.menuArrs).each(function(index,item){
			var lihtml = $('<li><h2>'+item.name+'</h2></li>');

			if(item.submenu && item.submenu.length > 0) {
				self._createSubMenu(item.submenu,lihtml);
			}
			$(ulhtml).append(lihtml);
		});
		$(container).append(ulhtml);
		
		_config.renderCallBack && $.isFunction(_config.renderCallBack) && _config.renderCallBack();
		
		// 处理层级缩进
		self._levelIndent(ulhtml);
		
		
	},
	/**
	 * 创建子菜单
	 * @param {array} 子菜单
	 * @param {lihtml} li项
	 */
	_createSubMenu: function(submenu,lihtml){
		var self = this,
			_config = self.config,
			_cache = self.cache;
		var subUl = $('<ul></ul>'),
			callee = arguments.callee,
			subLi;
		
		$(submenu).each(function(index,item){
			var url = item.url || 'javascript:void(0)';

			subLi = $('<li><a href="'+url+'"'+ ' target="mainbody">'+item.name+'</a></li>');
			if(item.submenu && item.submenu.length > 0) {
				$(subLi).children('a').css('position','left');
				//如果有孩子，则颜色加深
				$(subLi).children('a').css('background','#cccccc');
				
                callee(item.submenu, subLi);
			}
			$(subUl).append(subLi);
		});
		$(lihtml).append(subUl);
	},
	/**
	 * 处理层级缩进
	 */
	_levelIndent: function(ulList){
		var self = this,
			_config = self.config,
			_cache = self.cache,
			callee = arguments.callee;
	   
		var initTextIndent = 1,
			lev = 1,
			$oUl = $(ulList);
		var levcss={
				"padding-left":"0px","overflow":"hidden","display":"none","margin-right":"0px"
		};
		while($oUl.find('ul').length > 0){//有子级
			initTextIndent = parseInt(initTextIndent,10) + 1 + 'em'; //parseInt("12abc")   // 返回 12
			$oUl.children().children('ul').addClass('lev-' + lev)
						.children('li');//text-indent 属性规定文本块中首行文本的缩进
						//删除.css('text-indent', initTextIndent)
			
			$oUl = $oUl.children().children('ul');
			
			lev++;
		}
		
		$(ulList).find('ul').hide();
//		$(ulList).find('ul:first').show();	
		
		$(".lev-2").css(levcss);
		$(".lev-3").css(levcss);
		$(".lev-4").css(levcss);
		

	},
	/**
	 * 绑定事件
	 */
	_bindEnv: function(container) {
		var self = this,
			_config = self.config;

		$('h2,a',container).unbind(_config.type);
		$('h2,a',container).bind(_config.type,function(e){
			//$(this).css('background','#385385');
			if($(this).siblings('ul').length > 0) {
				
				$(this).siblings('ul').slideToggle('slow').end().children('img').toggleClass('unfold');
				//$(this).siblings('ul').slideToggle('slow').end().children('img').attr('src','images/isnot_15px.gif');
			}

		$(this).parent('li').siblings().find('ul').hide()
				   .end().find('img.unfold').removeClass('unfold');
			//$(this).parent('li').siblings().find('ul').hide().end().find('img').attr('src','images/ishas_15px.gif');

			_config.clickItemCallBack && $.isFunction(_config.clickItemCallBack) && _config.clickItemCallBack($(this));

		});
	}
 };
