function jsGetAge(strBirthday){       
    var returnAge;
    var strBirthdayArr=strBirthday.split("-");
    var birthYear = strBirthdayArr[0];
    var birthMonth = strBirthdayArr[1];
    var birthDay = strBirthdayArr[2];
    
    d = new Date();
    var nowYear = d.getFullYear();
    var nowMonth = d.getMonth() + 1;
    var nowDay = d.getDate();
    
    if(nowYear == birthYear){
        returnAge = 0;//同年 则为0岁
    }
    else{
        var ageDiff = nowYear - birthYear ; //年之差
        if(ageDiff > 0){
            if(nowMonth == birthMonth) {
                var dayDiff = nowDay - birthDay;//日之差
                if(dayDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
            else
            {
                var monthDiff = nowMonth - birthMonth;//月之差
                if(monthDiff < 0)
                {
                    returnAge = ageDiff - 1;
                }
                else
                {
                    returnAge = ageDiff ;
                }
            }
        }
        else
        {
            returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
        }
    }
    
    return returnAge;//返回周岁年龄
    
}

//根据生日的月份和日期，计算星座。
function getAstro(month,day){
  var s="魔羯水瓶双鱼白羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
  var arr=[20,19,21,21,21,22,23,23,23,23,22,22];
  return s.substr(month*2-(day<arr[month-1]?2:0),2)+"座";
}

function initData(){
	
	//初始化数据
	for ( var key in sysLabelObj) {
		
		if(key!='age' && key!='constellation' && key!='portrait'){
			if(key=='birthday'){
				var str=sysLabelObj[key].substr(0,10);
				$("[name='"+key+"']").val(str);
				
				var age=jsGetAge(str);
				var m;
				var d;
				if(str.charAt(5)=='0'){
					m=str.charAt(6);
				}else{
					m=str.charAt(5)+str.charAt(6);
				}
				if(str.charAt(8)=='0'){
					d=str.charAt(9);
				}else{
					d=str.charAt(8)+str.charAt(9);
				}
				var xz=getAstro(parseInt(m),parseInt(d));
				$("[name='age']").val(age);
				$("[name='constellation']").val(xz);
				continue;
			}
			$("[name='"+key+"']").val(sysLabelObj[key]);
			
		}
	}	
	//计算年龄和星座
	
}

function dateChange(dp){
	var dateStr= dp.cal.getDateStr();
	var age=jsGetAge(dateStr);
	
	var xz=getAstro(dp.cal.date.M,dp.cal.date.d);
	$("[name='age']").val(age);
	$("[name='constellation']").val(xz);
}





$(function(){
	//设置容器的高度
	$("#sys-container").css("height",frame);
	initData();
	
	
	
	
	$("#save-btn").click(function(){
		$.ajax({
			type: "post",
		    dataType: "text",
		    url: ctx+"/modifySysLabel",
		    data: $("#sys-form").serialize(),
		    success:function (result) {
		    	alert(result);
		    	parent.window.refreshSysLabelCache(userObj.account,"1");
		    }
		});
	});
	
	
	$("#portrait").change(function(){
		
		$.ajaxFileUpload({
            fileElementId: "portrait",    //需要上传的文件域的ID，即<input type="file">的ID。
            url: ctx+'/uploadPortrait', //后台方法的路径
            type: 'post',
            dataType: 'json',
            secureuri: false,//是否启用安全提交，默认为false。
            async : true,
            data:{
            	account:userObj.account
            },
            success: function(data) {
            	
            	parent.window.refreshSysLabelCache(userObj.account,"1");
            	parent.window.refreshPortrait();
            	
            	var obj = document.getElementById('portrait') ; 
            	obj.outerHTML=obj.outerHTML;
            },
            error: function(data, status, e) {
            	parent.window.refreshSysLabelCache(userObj.account,"1");
            	parent.window.refreshPortrait();
            	
            	var obj = document.getElementById('portrait') ; 
            	obj.outerHTML=obj.outerHTML;
            }
        });
	});
	
	
	
	
});


