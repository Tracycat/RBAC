/*************************************************
	Validator v1.05
	code by h7s8
	wfsr@msn.com
*************************************************/
 Validator = {
	
	Require : /.+/,						//未选择
	Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,		//信箱格式不正确
	//Phone : /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/,
	Phone : /^(0?[1-9]{2,3}-?)?[1-9]\d{6,7}(-\d{1,4})?$/,		//电话号码不正确
	Mobile : /^((\(\d{2,3}\))|(\d{3}\-))?1(3|5|8)\d{9}$/,		//手机号码不正确
	Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,//非法的Url
	IdCard : "this.IsIdCard(value)",		//身份证号码不正确
	Currency : /^\d+(\.\d+)?$/,//货币格式
	Number : /^\d+$/,
	Zip : /^[1-9]\d{5}$/,						//邮政编码不存在
	QQ : /^[1-9]\d{4,8}$/,						//QQ号码不存在
	Integer : /^[-\+]?\d+$/,//整数
	Double : /^[-\+]?\d+(\.\d+)?$/,
	English : /^[A-Za-z]+$/,					 //只允许英文字母
	Chinese :  /^[\u0391-\uFFE5]+$/,             //只允许中文
	Username : /^[a-z]\w{3,}$/i,
	UnSafe : /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/,
	IsSafe : function(str){return !this.UnSafe.test(str);},
	SafeString : "this.IsSafe(value)",//密码不符合安全规则
	Filter : "this.DoFilter(value, getAttribute('accept'))",
	Limit : "this.limit(value.length,getAttribute('min'),  getAttribute('max'))",//自我介绍内容必须在10个字之内
	LimitB : "this.limit(this.LenB(value), getAttribute('min'), getAttribute('max'))",//自传内容必须在[3,10]个字节之内
	Date : "this.IsDate(value, getAttribute('min'), getAttribute('format'))",//生日日期不存在
	Repeat : "value == document.getElementsByName(getAttribute('to'))[0].value",//两次输入不一致
	Range : "getAttribute('min') < (value|0) && (value|0) < getAttribute('max')",//年龄必须在18~28之间
	Compare : "this.compare(value,getAttribute('operator'),getAttribute('to'))",//年龄必须在18以上
	Custom : "this.Exec(value, getAttribute('regexp'))",//自定义正则表达式验证
	Group : "this.MustChecked(getAttribute('name'), getAttribute('min'), getAttribute('max'))",//必须选定一个省份
	ErrorItem : [document.forms[0]],
	ErrorMessage : ["错误原因：\t\t\t\t"],
	Validate : function(theForm, mode){					//第一个为表单对象,第二个参数为错误提示模式
		var obj = theForm || event.srcElement;		//Event 对象代表事件的状态，对于生成事件的 Window 对象、Document 对象或 Element 对象的引用。 ||?
		var count = obj.elements.length;			//Form 对象的elements集合是包含了表单中一个标签下的（input）所有元素的 数组--返回值是对象  的数组
		this.ErrorMessage.length = 1;
		this.ErrorItem.length = 1;
		this.ErrorItem[0] = obj;
		for(var i=0;i<count;i++){
			with(obj.elements[i]){		//with()可以用来引用特定对象中已经有的属性
				var _dataType = getAttribute("dataType");
				//对于对象、数组、null 返回的值是 object; 如果运算数是没有定义的（比如说不存在的变量、函数或者undefined），将返回undefined
				if(typeof(_dataType) == "object" || typeof(this[_dataType]) == "undefined")  continue;//this[_dataType])指的是前面的正则表达式的对象
				this.ClearState(obj.elements[i]);		//清除指定表单项elem的错误提示信息。
				if(getAttribute("require") == "false" && value == "") continue;//如果不是必填项且输入为空时
				switch(_dataType){
					case "IdCard" :
					case "Date" :
					case "Repeat" :
					case "Range" :
					case "Compare" :       
					case "Custom" :
					case "Group" : 
					case "Limit" :
					case "LimitB" :
					case "SafeString" :
					case "Filter" :
					//alert(this[_dataType]);
					//alert(eval(this[_dataType]));			//this[_dataType]返回的是对应的表达式
						if(!eval(this[_dataType]))	{				//eval会运行其表达式，若结果不符合表达式则返回false;若结果正确的话则返回true
						
							this.AddError(i, getAttribute("msg"));
						}
						break;
					default :
						if(!this[_dataType].test(value)){			//test是正则表达式的方法，测试输入是否符合正则表达式
							this.AddError(i, getAttribute("msg"));
						}
						break;
				}
			}
		}
		if(this.ErrorMessage.length > 1){
			mode = mode || 1;
			var errCount = this.ErrorItem.length;
			switch(mode){
			case 2 :
				for(var i=1;i<errCount;i++)
					this.ErrorItem[i].style.color = "red";		//改颜色//向下执行，直到找到break
			case 1 :
				alert(this.ErrorMessage.join("\n"));		//每输出一项错误内容增加一个换行符
				this.ErrorItem[1].focus();					//就是输出完错误原因后，会在第一项拥有一个输入焦点
				break;											
			case 3 :
				for(var i=1;i<errCount;i++){
				try{
					var span = document.createElement("SPAN");//创建一个元素节点
					span.className = "__ErrorMessagePanel";//为新节点规定名称
					span.style.color = "red";				//样式
					this.ErrorItem[i].parentNode.appendChild(span);//找到父标签，在父标签后面嵌套一个子标签（即刚刚创建的新节点）
					span.innerHTML = this.ErrorMessage[i].replace(/\d+:/,"*");   //将默认值设置为错误原因；replace（a,b）每当a被找到，则将a替换为b
					}
					catch(e){alert(e.description);}//输出内容标签
				}
				this.ErrorItem[1].focus();			//
				break;
			default :
				alert(this.ErrorMessage.join("\n"));		//
				break;
			}
			return false;
		}
		return true;
	},
	limit : function(len,min, max){
		min = min || 0;
		max = max || Number.MAX_VALUE;		//MAX_VALUE　　可表示的最大数字
		return min <= len && len <= max;
	},
	LenB : function(str){
		return str.replace(/[^\x00-\xff]/g,"**").length;
	},
	ClearState : function(elem){
		with(elem){
			if(style.color == "red")
				style.color = "";
			var lastNode = parentNode.childNodes[parentNode.childNodes.length-1];//（父节点的子节点）取的子节点是新增加的标签
			if(lastNode.className == "__ErrorMessagePanel")
				parentNode.removeChild(lastNode);		//删除此子节点
		}
	},
	AddError : function(index, str){
		this.ErrorItem[this.ErrorItem.length] = this.ErrorItem[0].elements[index];//在ErrorItem[]表单数组里面新增一个元素，这个元素为这个表单下的一个标签内容
		this.ErrorMessage[this.ErrorMessage.length] = this.ErrorMessage.length + ":" + str;//在ErrorMessage[]错误信息数组中新增一个元素，这个元素为（编号：错误信息（就是冒号，别多想））
	},
	Exec : function(op, reg){
		return new RegExp(reg,"g").test(op);
	},
	compare : function(op1,operator,op2){
		switch (operator) {
			case "NotEqual":
				return (op1 != op2);
			case "GreaterThan":
				return (op1 > op2);
			case "GreaterThanEqual":
				return (op1 >= op2);
			case "LessThan":
				return (op1 < op2);
			case "LessThanEqual":
				return (op1 <= op2);
			default:
				return (op1 == op2);            
		}
	},
	MustChecked : function(name, min, max){
		var groups = document.getElementsByName(name);
		var hasChecked = 0;
		min = min || 1;
		max = max || groups.length;
		for(var i=groups.length-1;i>=0;i--)
			if(groups[i].checked) hasChecked++;
		return min <= hasChecked && hasChecked <= max;
	},
	DoFilter : function(input, filter){
		return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, filter.split(/\s*,\s*/).join("|")), "gi").test(input);
	},
	IsIdCard : function(number){
		var date, Ai;
		var verify = "10x98765432";
		var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
		var area = ['','','','','','','','','','','','','','?','?','?','','','','','','','','','','','','','','','','?','','?','?','','','?','','','','','','','?','','','','','','','?','','','','','','','','','','','','?','','?','','','','','','?','','','','','','','','','','','','','','','','','','','',''];
		var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/i);
		if(re == null) return false;
		if(re[1] >= area.length || area[re[1]] == "") return false;
		if(re[2].length == 12){
			Ai = number.substr(0, 17);
			date = [re[9], re[10], re[11]].join("-");
		}
		else{
			Ai = number.substr(0, 6) + "19" + number.substr(6);
			date = ["19" + re[4], re[5], re[6]].join("-");
		}
		if(!this.IsDate(date, "ymd")) return false;
		var sum = 0;
		for(var i = 0;i<=16;i++){
			sum += Ai.charAt(i) * Wi[i];
		}
		Ai +=  verify.charAt(sum%11);
		return (number.length ==15 || number.length == 18 && number == Ai);
	},
	IsDate : function(op, formatString){
		formatString = formatString || "ymd";
		var m, year, month, day;
		switch(formatString){
			case "ymd" :
				m = op.match(new RegExp("^((\\d{4})|(\\d{2}))([-./])(\\d{1,2})\\4(\\d{1,2})$"));
				if(m == null ) return false;
				day = m[6];
				month = m[5]*1;
				year =  (m[2].length == 4) ? m[2] : GetFullYear(parseInt(m[3], 10));
				break;
			case "dmy" :
				m = op.match(new RegExp("^(\\d{1,2})([-./])(\\d{1,2})\\2((\\d{4})|(\\d{2}))$"));
				if(m == null ) return false;
				day = m[1];
				month = m[3]*1;
				year = (m[5].length == 4) ? m[5] : GetFullYear(parseInt(m[6], 10));
				break;
			default :
				break;
		}
		if(!parseInt(month)) return false;
		month = month==0 ?12:month;
		var date = new Date(year, month-1, day);
        return (typeof(date) == "object" && year == date.getFullYear() && month == (date.getMonth()+1) && day == date.getDate());
		function GetFullYear(y){return ((y<30 ? "20" : "19") + y)|0;}
	}
 }