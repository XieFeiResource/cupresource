$(document).ready(function(){
	$(".header-search button").css("background", "none");
	
	//页面输入搜索
	$("#searchInput").bind("keypress", function(event){
		if(event.keyCode==13){
			$("#searchForm").submit();
		}
	});
	
	//导航栏输入搜索
	$(".header-search").bind("keypress", function(event){
		if(event.keyCode==13){
			$(".header-search").submit();
		}
	});
	
//=============================================================================
	
	//页面中间搜索框功能模块
	$("#searchInput").focus(function(){
		$("#searchInput").autocomplete({  
	    	minLength: 0,
	        source: function( request, response ) {  
	        $.ajax({
	           type : "POST",
	           dataType : "JSON",
	           url : 'CompanyServlet',
	           data : {
	           		"CorpName" : $("#searchInput").val(),
	           		"method":"searchName"
	           	},
	           success : function(data) {
	           		console.log(data);  
                    response( $.map( data, function( item ) {  
						return {  
							label: item.corp_NAME,  
                            value: item.corp_NAME  
                        }  
                     }));  
				}  
            });
          	},  
            focus: function( event, ui ) {  
            	$("#searchInput").val( ui.item.label );  
                $("#rpId").val( ui.item.value );  
                return false;  
            },  
            select: function( event, ui ) {  
                $("#searchInput").val( ui.item.label );  
                $("#rpId").val( ui.item.value );  
                return false; 
            }    
         }); 
	});
//=================================================================================
	//导航栏部分模糊匹配公司名
	$("#searchInputTop").focus(function(){
		$("#searchInputTop").autocomplete({  
	    	minLength: 0,
	        source: function( request, response ) {  
	        $.ajax({
	           type : "POST",
	           dataType : "JSON",
	           url : 'CompanyServlet',
	           data : {
	           		"CorpName" : $("#searchInputTop").val(),
	           		"method":"searchName"
	           	},
	           success : function(data) {
	           		console.log(data);  
                    response( $.map( data, function( item ) {  
						return {  
							label: item.corp_NAME,  
                            value: item.corp_NAME  
                        }  
                     }));  
				}  
            });
          	},  
            focus: function( event, ui ) {  
            	$("#searchInputTop").val( ui.item.label );  
                $("#rpId").val( ui.item.value );  
                return false;  
            },  
            select: function( event, ui ) {  
                $("#searchInputTop").val( ui.item.label );  
                $("#rpId").val( ui.item.value );  
                return false; 
            }    
         }); 
	});
//=================================================================================

});
//=================================================================================
//显示公司对外投资族谱  
var myChart = echarts.init(document.getElementById('showzupu'));
function showzupu() {
//	var company=$(".companyInput").text();
//	var stock=$(".stockInput").text();
	//数据加载完之前先显示一段简单的loading动画
	myChart.showLoading();
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : 'CompanyServlet',
		data : {
			"CORP_NAME" :  $("#CORP_NAME").val(),
			"method" : "touzizupu",
			"companyLevel" : j,
			"stockLevel" : i
		},
		success : function(result) {
			console.log(result);
			var r=getJsonTree(result,"");
			myChart.hideLoading(); 
			drawTouziTree(r);
			
//			//在返回的数据中取出判断是否还有更多的关系
//			if(result[result.length-1].value="false"){
//				//保持之前的层级不变
//				$(".stockInput").text(step[i-1]);
//				i=i-1;
//				alert("股东没有更多关系了");
//			}
//			if(result[result.length-2].value="false"){
//				$(".companyInput").txt(step[j-1]);
//				j=j-1;
//				alert("对外投资没有更多的关系了");
//			}
			
		}
	})

} 
//显示投资族谱树
function drawTouziTree(treeData) {
	var model=myChart.getModel();
	myChart.setOption({
		tooltip : {
			trigger : 'item',
			triggerOn : 'mousemove'
		},
		series : [ {
			type : 'tree',
			data : treeData,
			top : '5%',
			layout : 'radial',
			symbol : 'circle',
			symbolSize : 10,
			itemStyle : {//树图中每个节点的样式
				normal : {
					color : '#ffffff',
					borderColor : '#b03a5b',
					borderWidth : 2
				},
				emphasis : {
					color : '#000',
					borderColor : '#b03a5b',
					borderWidth : 5
				}
			},
			label: {
				show:true,
				formatter: function(params) {
		              var result = "";
		              	if(params.name!=undefined){
		              		result+=params.name+"\n";
		              }
		              	if(params.value!=undefined){
		              		result+=params.value+"\n";
		              }
		              return result;
		           },
			},
			initialTreeDepth : 9,
			animationDurationUpdate : 750
		} ]

	});
}
//=================================================================================
//处理ajax返回的字符串为符合echarts规范的树状串
var getJsonTree=function(data,parentId){
  var itemArr=[];
  for(var i=0;i<data.length;i++){ 
      var node=data[i];
      //data.splice(i, 1)
       if(node.parentId==parentId ){ 
          var newNode={name:node.id,value:node.value,children:getJsonTree(data,node.id)};
          itemArr.push(newNode);              
       }
  }
  return itemArr;
}
//=================================================================================
//显示企业族谱信息
var corpChart = echarts.init(document.getElementById('corpzupu'));
function corpzupu(){
	corpChart.showLoading();
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : "CompanyServlet",
		data : {
			"method" : "corpzupu",
			"CORP_ORG" : $("#CORP_ORG").val(),
			"CORP_SEQ_ID" : $("#CORP_SEQ_ID").val(),
			"CORP_NAME" :  $("#CORP_NAME").val(),
			
		},
		success : function(result){
			console.log(result);
			res=getJsonTree(result,"");
			console.log(res);
			corpChart.hideLoading();
			drawCorpTree(res);
		}
	});
}

//显示企业族谱树
function drawCorpTree(treeData) {
	corpChart.setOption({
		tooltip : {
			trigger : 'item',
			triggerOn : 'mousemove'
		},
		series : [ {
			type : 'tree',
			data : treeData,
			top : '5%',
			layout : 'radial',
			symbol : 'circle',
			symbolSize : 10,
			itemStyle : {//树图中每个节点的样式
				normal : {
					color : '#ffffff',
					borderColor : '#b03a5b',
					borderWidth : 2
				},
				emphasis : {
					color : '#000',
					borderColor : '#b03a5b',
					borderWidth : 5
				}
			},
			label: {
				show:true,
				formatter: function(params) {
		              var result = "";
		              	if(params.name!=undefined){
		              		result+=params.name+"\n";
		              }
		              	if(params.value!=undefined){
		              		result+=params.value+"\n";
		              }
		              return result;
		           },
			},
			initialTreeDepth : 3,
			animationDurationUpdate : 750
		} ]

	});
}
//疑似关系
var yisiguanxiChart = echarts.init(document.getElementById('yisiguanxi'));
function yisiguanxi(){
	yisiguanxiChart.showLoading();
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : "CompanyServlet",
		data : {
			"method" : "yisiguanxi",
			"CORP_NAME" :  $("#CORP_NAME").val(),
			
		},
		success : function(result){
			console.log(result);
			yisiguanxiChart.hideLoading();
			treeDatadata=result[1]
			treeDatalinks=result[0]
			drawGraphTree(treeDatadata,treeDatalinks);
		}
	});
}
//画疑似关系图
function drawGraphTree(treeDatadata,treeDatalinks) {
	yisiguanxiChart.setOption({
		    title: {
		        text: '疑似关系'
		    },
		    tooltip: {},
		    animationDurationUpdate: 1500,
		    animationEasingUpdate: 'quinticInOut',
		    label: {
		        normal: {
		            show: true,
		            textStyle: {
		                fontSize: 12
		            },
		        }
		    },
		    legend: {
		        x: "center",
		        show: true,
		        data: ["股东", "公司", "高管"]
		    },
		    series: [

		        {
		            type: 'graph',
		            layout: 'force',
		            symbolSize: 45,
		            focusNodeAdjacency: true,
		            roam: true,
		            categories: [{
		                name: '股东',
		                itemStyle: {
		                    normal: {
		                        color: "#009800",
		                    }
		                }
		            }, {
		                name: '公司',
		                itemStyle: {
		                    normal: {
		                        color: "#4592FF",
		                    }
		                }
		            }, {
		                name: '高管',
		                itemStyle: {
		                    normal: {
		                        color: "#3592F",
		                    }
		                }
		            }],
		            label: {
		                normal: {
		                    show: true,
		                    textStyle: {
		                        fontSize: 12
		                    },
		                }
		            },
		            force: {
		                repulsion: 1000
		            },
		            edgeSymbolSize: [4, 50],
		            edgeLabel: {
		                normal: {
		                    show: true,
		                    textStyle: {
		                        fontSize: 10
		                    },
		                    formatter: "{c}"
		                }
		            },
		            data: treeDatadata,
		            links: treeDatalinks,
		            lineStyle: {
		                normal: {
		                    opacity: 0.9,
		                    width: 1,
		                    curveness: 0
		                }
		            }
		        }
		    ]
		});
}
//=================================================================================
//投资族谱中的层级切换
var step= ['一层','二层','三层','四层','五层','六层','七层','八层','九层','十层'];
var stockInput = $(".stockInput");
var conpanyInput = $(".companyInput");
var i = 0;
var j = 0;
function shang(){
  i++;
  stockInput.text(step[i]);
  if(i>=5) i=5;
  
  showzupu();
}
function xia(){
  i--;
  stockInput.text(step[i]);
  if(i<=0) i=0;
  
  showzupu();
}
function shang1(){
  j++;
  conpanyInput.text(step[j]);
  if(j>=5) j=5;
  
  showzupu();
}
function xia1(){
  j--;
  conpanyInput.text(step[j]);
  if(j<=0) j=0;
  
  showzupu();
}

//=================================================================================

//页面选项卡切换
function changestatus(a,b,c,d,e,f){
	$(".container").css('display', a)
	$(".page1").css('display', b);
	$(".page2").css('display', c);
	$(".page3").css('display', d);
	$(".page4").css('display', e);
	$(".page5").css('display', f);
}
function changePage(obj){
	if(obj.innerHTML=="基本信息"){
		changestatus("none","block","none","none","none","none");
	}if(obj.innerHTML=="股权结构"){
		changestatus("none","none","block","none","none","none");
	}if(obj.innerHTML=="投资族谱"){
		showzupu();
		changestatus("none","none","none","block","none","none");
	}if(obj.innerHTML=="企业族谱"){
		corpzupu();
		changestatus("none","none","none","none","block","none");
	}if(obj.innerHTML=="疑似关系"){
		yisiguanxi();
		changestatus("none","none","none","none","none","block");
	}if(obj.innerHTML=="Home"){
		changestatus("block","none","none","none","none","none");
	}
	
}

