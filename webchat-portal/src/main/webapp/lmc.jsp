<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="./js/lmc_tree.json"></script>
<script type="text/javascript">
	//获取参数
	var nodeid=parseInt("${param.nodeid}");
	function initTree(){
		$('#lmc-tree').treeview({
		    data:lmcTreeData,
		    showTags:true,
		    onhoverColor:'white',
		    selectedBackColor: 'green',
		    collapseIcon: "glyphicon glyphicon-chevron-down",
		    expandIcon: "glyphicon glyphicon-chevron-right"
		});
		//使tree为身份验证标签的被选中
		$('#lmc-tree').treeview('toggleNodeSelected', [ nodeid, { silent: true } ]);
		
		$('#lmc-tree').on('nodeSelected',function(event, data) {
			
			if(data.nodeId==1){
				location.href='lmc_auth.jsp';
			}
			if(data.nodeId==2){
				location.href='lmc_sys.jsp';
			}
			if(data.nodeId==3){
				location.href='lmc_personal.jsp';
			}
			if(data.nodeId==5){
				location.href='lmc_permission.jsp';
			}
		});
	}
	
	
	$(function(){
		
		initTree();
		
		
		
		
	});
	

</script>

<div id="lmc-tree" style="height: 100%;width: 20%;float: left;">
</div>

	
