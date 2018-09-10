var My=function(){};
/**
 * 打开窗口
 * 参数说明
 * 参数opts json格式的参数，具体说明如下：
 * 		{ 			
 * 			title : '窗口标题',
 * 			contentId : 窗口内容的id
 * 			width : 窗口宽度
 * 			height : 窗口高度
 * 			 		
 * 		}
 *   handler 处理服务器响应数据的函数
 * 返回 窗口编号
 */
My.open = function(opts){
	
	var width = opts.width ? opts.width : 700;
	var height = opts.height ? opts.height : 470;
	var content = opts.contentId ? $('#'+opts.contentId) : '';
	
	var layerIdx = layer.open({
		type : 1,
		anim: new Date().getTime() % 7,
		title : opts.title,
		area : [ width+'px', height+'px' ],
		content : content,
		zIndex:100 //将层次降低，以使sweetalert能够在layer之上
	});
	
	return layerIdx;
};

/**
 * 关闭窗口 
 * 参数 窗口编号
 */
My.close = function(dlg){
	layer.close(dlg);
}

/**
 * 加载数据
 * 参数说明
 * 参数opts json格式的参数，具体说明如下：
 * 		{ 			
 * 			url : '请求地址',
 * 			data : '请求参数' 		
 * 		}
 *   handler 处理服务器响应数据的函数
 */
My.loadData=function(opts,handler){
	
	opts.handler = handler ;
	
	this.ajax(opts);
};

/**
 * 带有上传文件的操作
 * 参数说明
 * 	参数opts json格式的参数，具体说明如下：
 * 		{
 * 			title : '提示信息标题',
 * 			text : '提示信息文字',
 * 			url : '请求地址',
 * 			data : '请求参数'
 * 		
 * 		}
 * 	参数 okHander 请求成功后执行的函数
 */
My.operateMultipartData = function(opts,okHandler){
	opts.contentType=false
	opts.processData=false;
	
	this.operate(opts,okHandler);
}

/**
 * 参数说明
 * 	参数opts json格式的参数，具体说明如下：
 * 		{
 * 			title : '提示信息标题',
 * 			text : '提示信息文字',
 * 			url : '请求地址',
 * 			data : '请求参数'
 * 		
 * 		}
 * 	参数 okHander 请求成功后执行的函数
 */
My.operate=function(opts,okHandler){
	
	opts.dataType = 'JSON';
	opts.handler = function(result){
		
		if (result.success) {
			
			swal({
				 text: result.message,											 
				 icon: "success",
				 buttons:false,
				 timer:1500 
			});
			
			if(okHandler)okHandler(result);
			
		} else {
			swal({
				 title: result.message,											 
				 icon: "error",
				 button: "确定"
			});
		}
		
	};
	
	this.ajaxWithConfirm(opts);
};


My.ajaxWithConfirm=function(options){
	
	
	if((!options.title) && (!options.text)){
		this.ajax(options);
		return;
	}
	
	
	var warnTitle = options.title?options.title:'';
	var warnText = options.text?options.text:'';
	
	
	
	swal({
		title: warnTitle,
		text: warnText,
		icon: "warning",
		buttons: ["取消",'确定'],
		dangerMode: true,
	})
	.then((ok) => {
		
		if (ok) {
			this.ajax(options);
		}
		 
		  
	})						
	.catch(err => {
		console.log(err);
		if (err) {
			swal({
				 title: "出错了！请排查错误。",											 
				 icon: "error",
				 button: "确定"
			});
		}
	});
}

My.ajax=function(options){
	
	var url = options.url?options.url:undefined;
	var method = options.method?options.method:'POST';
	var data = options.data?options.data:undefined;
	var type = options.dataType?options.dataType:'JSON';
	var contentType = options.contentType===false ? false : ( options.contentType?options.contentType:'application/x-www-form-urlencoded');
	var processData = options.processData===false ? false : (options.processData?options.processData:true);
	
	var myhandler = options.handler && typeof(options.handler)==='function' ? options.handler : undefined;
	
	var index = layer.load();
	try{
		console.log({
			url:url,
			data:data,
			method:method,
			dataType:type,
			contentType:contentType,
			processData:processData
		});
		$.ajax({
			url:url,
			data:data,
			method:method,
			dataType:type,
			contentType:contentType,
			processData:processData
		}).done((data) => {
			layer.close(index);
			try{
				
				if(myhandler){
					myhandler(data);
				}
				
			}catch(err){
				swal({
					 title: "前端报告错误！您的JavaScript代码可能出错了。",											 
					 icon: "error",
					 button: "确定"
				});
			}
			
		}).fail((xhr) => {
			layer.close(index);
			swal({
				 title: "服务端报告错误！错误代码："+xhr.status,											 
				 icon: "error",
				 button: "确定"
			});
			//layer.msg("服务端报告错误！错误代码："+xhr.status, {icon: 5, time: 3000});						
		});
		
	}catch(err){
		layer.close(index);
		console.log(err);
		swal({
			 title: "前端报告错误！您的JavaScript代码可能出错了。",											 
			 icon: "error",
			 button: "确定"
		});
		//layer.msg("前端报告错误！您的JavaScript代码可能出错了。", {icon: 5, time: 3000});	
	}
	
	
};